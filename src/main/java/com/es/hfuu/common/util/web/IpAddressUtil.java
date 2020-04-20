package com.es.hfuu.common.util.web;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @ClassName IpAddressUtil
 * @Description 获得用户远程地址
 * @Author lsx
 * @Date 2019/11/7
 **/
public class IpAddressUtil {

	/**
	 * x-forwarded-for
	 */
	private static String X_FORWARDED_FOR = "x-forwarded-for";
	/**
	 * Proxy-Client-IP
	 */
	private static String PROXY_CLIENT_IP = "Proxy-Client-IP";
	/**
	 * WL-Proxy-Client-IP
	 */
	private static String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
	/**
	 * unknown
	 */
	private static String UNKNOWN_IP = "unknown";
	/**
	 * 默认的本地IP
	 */
	private static String DEFAULT_IP = "127.0.0.1";
	/**
	 * 本地的IPv6的Ip
	 */
	private static String LOCAL_V6_IP = "0:0:0:0:0:0:0:1";

	/**
	 * WINDOWS系统
	 */
	public static final String WINDOWS = "windows";

	/**
	 * 获取IP地址
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(X_FORWARDED_FOR);
		if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
			ip = request.getHeader(PROXY_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
			ip = request.getHeader(WL_PROXY_CLIENT_IP);
		}
		if (ip == null || ip.length() == 0 || UNKNOWN_IP.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return LOCAL_V6_IP.equals(ip) ? DEFAULT_IP : ip;
	}

	/**
	 * 获取本地IP地址
	 *
	 */
	public static String getLocalIP() throws UnknownHostException, SocketException {
		if (isWindowsOS()) {
			return InetAddress.getLocalHost().getHostAddress();
		} else {
			return getLinuxLocalIp();
		}
	}

	/**
	 * 判断操作系统是否是Windows
	 * @Title: isWindowsOS
	 * @return true 是 false 否
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().contains(WINDOWS)) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取Linux下的IP地址
	 *
	 * @return IP地址
	 * @throws SocketException
	 */
	private static String getLinuxLocalIp() throws SocketException {
		String ip = "";
		for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
			NetworkInterface intf = en.nextElement();
			String name = intf.getName();
			if (!name.contains("docker") && !name.contains("lo")) {
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						String ipaddress = inetAddress.getHostAddress();
						if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
							ip = ipaddress;
							System.out.println(ipaddress);
						}
					}
				}
			}
		}
		return ip;
	}
}
