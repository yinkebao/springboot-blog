package com.es.hfuu.common.util.redis.util;

import com.es.hfuu.common.domain.Session;
import com.es.hfuu.common.util.array.ArrayUtil;
import com.es.hfuu.common.util.base.StringUtil;
import com.es.hfuu.common.util.base.UuidUtil;
import com.es.hfuu.common.util.exception.base.BusinessValidationException;
import com.es.hfuu.common.util.exception.base.SystemUtilException;
import com.es.hfuu.common.util.redis.constans.RedisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description Redis的工具类
 * @Author ykb
 * @Date 2019/12/5
 */
@Component("sessionRedisUtil")
public class SessionRedisUtil {
    @Autowired
    @Qualifier("sessionRedisTemplate")
    private RedisTemplate<String, Session> sessionRedisTemplate;

    @Autowired
    @Qualifier("valueOperations")
    private ValueOperations<String, Session> valueOperations;

    @Autowired
    @Qualifier("hashOperations")
    private HashOperations<String, String, Session> hashOperations;

    @Autowired
    @Qualifier("listOperations")
    private ListOperations<String, Session> listOperations;

    @Autowired
    @Qualifier("setOperations")
    private SetOperations<String, Session> setOperations;

    @Autowired
    @Qualifier("zSetOperations")
    private ZSetOperations<String, Session> zSetOperations;

    /**
     * @Title: generateSessionId
     * @Description: 使用Uuid构建自定义的sessionId；
     * @return String
     */
    public String generateSessionId() {
        return RedisConstants.PREFIX_SESSION + UuidUtil.uuid();
    }

    /**
     * @Title: saveSession
     * @Description: 将Session保存到redis中
     * @param session
     * @return void 无
     */
    public void saveSession(Session session){
        if(null == session || StringUtil.isEmpty(session.getSessionId()) || null == session.getUserId() || null == session.getAccessTime()){
            return;
        }
        try {
            List<Object> txResults = sessionRedisTemplate.execute(new SessionCallback<List<Object>>() {
                @Override
                public List<Object> execute(RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    operations.opsForHash().put(RedisConstants.SYSTEM_REDIS_SESSION_HASH_CACHE, session.getSessionId(), session);
                    operations.opsForZSet().add(RedisConstants.SYSTEM_REDIS_SESSION_SCORE_CACHE, session.getSessionId(), session.getAccessTime().getTime());
                    operations.opsForSet().add(RedisConstants.SYSTEM_REDIS_USR_ONLINE_CACHE, session.getUserId());
                    return operations.exec();
                }
            });
        }catch (Exception e){
            throw new SystemUtilException("将会话信息保存到redis失败", e);
        }
    }

    /**
     * @Title:
     * @Description: 将Session从redis中删除
     * @param sessionId
     * @param userId
     * @return void 无
     */
    public void deleteSession(String[] sessionId,Long[] userId){
        if(ArrayUtil.isEmpty(sessionId) && ArrayUtil.isEmpty(userId)){
            return;
        }
        try {
            List<Object> txResults = sessionRedisTemplate.execute(new SessionCallback<List<Object>>() {
                @Override
                public List<Object> execute(RedisOperations operations) throws DataAccessException {
                    operations.multi();
                    if (ArrayUtil.isNotEmpty(sessionId)) {
                        operations.opsForHash().delete(RedisConstants.SYSTEM_REDIS_SESSION_HASH_CACHE, sessionId);
                        operations.opsForHash().delete(RedisConstants.SHIRO_REDIS_SUBJECT_HASH_CACHE, sessionId);
                        operations.opsForZSet().remove(RedisConstants.SYSTEM_REDIS_SESSION_SCORE_CACHE, sessionId);
                    }
                    if (ArrayUtil.isNotEmpty(userId)) {
                        operations.opsForSet().add(RedisConstants.SYSTEM_REDIS_USR_ONLINE_CACHE, userId);
                    }
                    return operations.exec();
                }
            });
        } catch (Exception e){
            throw new SystemUtilException("删除redis中的会话信息失败", e);
        }
    }

    /**
     * @Title: getSessionBySessionId
     * @Description: 根据sessionId从redis中获取session对象
     * @param sessionId
     * @return Session
     */
    public Session getSessionBySessionId(String sessionId){
        try {
            return hashOperations.get(RedisConstants.SYSTEM_REDIS_SESSION_HASH_CACHE, sessionId);
        }catch (Exception e){
            throw new SystemUtilException("从redis中查询会话信息失败", e);
        }
    }

    //=============================common============================
    /**
     * @Title: expire
     * @Description: 指定缓存失效时间
     * @param key 缓存的键
     * @param time 失效时间，单位秒
     * @return boolean 设置失效成功：true，失败：false
     */
    public boolean expire(String key, long time){
        if(time>0){
            sessionRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * @Title: getExpire
     * @Description: 根据key获取缓存过期的时间
     * @param key 缓存的键，不能为null
     * @return long 时间，单位(秒)，返回0代表为永久有效
     */
    public long getExpire(String key){
        return sessionRedisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * @Title: hasKey
     * @Description: 判断key是否存在
     * @param key 缓存的键，不能为空
     * @return boolean 存在：true，不存在：false
     */
    public boolean hasKey(String key){
        return sessionRedisTemplate.hasKey(key);
    }

    /**
     * @Title: del
     * @Description: 根据key删除缓存
     * @param key 键或者键数组
     * @return null
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key){
        if(key != null && key.length > 0){
            if(key.length==1){
                sessionRedisTemplate.delete(key[0]);
            }else{
                sessionRedisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================
    /**
     * @Title: get
     * @Description: 根据key获取缓存内容
     * @param key 键
     * @return Session 值
     */
    public Session get(String key){
        return key == null ? null : valueOperations.get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    /**
     * @Title: set
     * @Description: 将对象放入到缓存中
     * @param key 键
     * @param value 值
     * @return 成功返回true
     */
    public boolean set(String key, Session value) {
        valueOperations.set(key, value);
        return true;
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Session value, long time){
        boolean bol = true;
        if(time>0){
            valueOperations.set(key, value, time, TimeUnit.SECONDS);
        }else{
            bol = set(key, value);
        }
        return bol;
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta){
        if(delta<0){
            throw new BusinessValidationException("递增因子必须大于0");
        }
        return valueOperations.increment(key, delta);
    }

    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta){
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return valueOperations.increment(key, -delta);
    }

    //================================ Hash =================================

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<String, Session> hmget(String key){
        return hashOperations.entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Session> map){
        try {
            hashOperations.putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Session> map, long time){
        try {
            hashOperations.putAll(key, map);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Session value) {
        try {
            hashOperations.put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Session value, long time) {
        try {
            hashOperations.put(key, item, value);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Session... item){
        hashOperations.delete(key,item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item){
        return hashOperations.hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item,double by){
        return hashOperations.increment(key, item, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item,double by){
        return hashOperations.increment(key, item,-by);
    }

    //============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public Set<Session> sGet(String key){
        try {
            return setOperations.members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Session value){
        try {
            return setOperations.isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Session...values) {
        try {
            return setOperations.add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sSetAndTime(String key, long time, Session...values) {
        Long count = setOperations.add(key, values);
        if(time>0) {
            expire(key, time);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key){
        return setOperations.size(key);
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Session...values) {
        return setOperations.remove(key, values);
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Session> lGet(String key, long start, long end){
        return listOperations.range(key, start, end);
    }

    /**
     * 获取list缓存的所有内容
     * @param key
     * @return
     */
    public List<Session> lGetAll(String key){
        return lGet(key,0,-1);
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(String key){
        return listOperations.size(key);
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Session lGetIndex(String key, long index){
        return listOperations.index(key, index);
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, Session value) {
        listOperations.rightPush(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, Session value, long time) {
        listOperations.rightPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Session> value) {
        listOperations.rightPushAll(key, value);
        return true;
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Session> value, long time) {
        try {
            listOperations.rightPushAll(key, value);
            if (time > 0)
            {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Session value) {
        listOperations.set(key, index, value);
        return true;
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Session value) {
        return listOperations.remove(key, count, value);
    }
}
