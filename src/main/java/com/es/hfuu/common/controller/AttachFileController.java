package com.es.hfuu.common.controller;

import static com.es.hfuu.common.util.exception.util.ExceptionUtil.providerServiceInvokeFunction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.es.hfuu.common.domain.AttachFile;
import com.es.hfuu.common.service.AttachFileService;
import com.es.hfuu.common.vo.EsResult;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FilesController
 * @Description
 * @Author ykb
 * @Date 2020/3/22
 */
@Controller
@RequestMapping("/system/file")
public class AttachFileController extends BaseController{

  @Autowired
  private AttachFileService attachFileService;

  @PostMapping(value = "/uploadImg")
  @ResponseBody
  public String uploadImg(@RequestParam("file") MultipartFile file) {
    AttachFile attachFile = new AttachFile();
    try {
      String fileName = System.currentTimeMillis() + file.getOriginalFilename();
      String destFileName = System.getProperty("user.dir") + "//src//main//resources//static//uploadFiles//img" + File.separator + fileName;
      File destFile = new File(destFileName);
      destFile.getParentFile().mkdirs();
      file.transferTo(destFile);
      attachFile.setFileName(fileName);
      attachFile.setModuleKey("img");
      attachFile.setPhysicsFileUrl("/uploadFiles/img/"+fileName);
      attachFile.setModuleObjectId(0L);
    } catch (IOException e) {
      e.printStackTrace();
    }
    JSONObject json = new JSONObject();
    providerServiceInvokeFunction(attachFileService::saveAttachFile,attachFile);
    json.put("errno",0);
    String[] img = {attachFile.getPhysicsFileUrl()};
    json.put("data",img);
    return json.toJSONString();
  }
}
