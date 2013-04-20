/**
 * <p>文件名:	WebMVC.java</p>
 * <p>版权:		详见产品版权说明书</p>
 * <p>公司:		YMateSoft Co., Ltd.</p>
 * <p>项目名：	yMatePlatform_V2_1</p>
 * <p>作者：		刘镇(suninformation@163.com)</p>
 */
package net.ymate.platform.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import net.ymate.platform.commons.i18n.I18N;
import net.ymate.platform.commons.i18n.II18NEventHandler;
import net.ymate.platform.configuration.Cfgs;
import net.ymate.platform.mvc.MVC;
import net.ymate.platform.mvc.web.impl.WebRequestProcessor;




/**
 * <p>
 * WebMVC
 * </p>
 * <p>
 * 基于Web应用的MVC框架核心管理器；
 * </p>
 * 
 * @author 刘镇(suninformation@163.com)
 * @version 0.0.0
 *          <table style="border:1px solid gray;">
 *          <tr>
 *          <th width="100px">版本号</th><th width="100px">动作</th><th
 *          width="100px">修改人</th><th width="100px">修改时间</th>
 *          </tr>
 *          <!-- 以 Table 方式书写修改历史 -->
 *          <tr>
 *          <td>0.0.0</td>
 *          <td>创建类</td>
 *          <td>刘镇</td>
 *          <td>2012-12-7下午10:23:39</td>
 *          </tr>
 *          </table>
 */
public class WebMVC extends MVC {

	/**
	 * 初始化WebMVC管理器
	 * 
	 * @param config
	 */
	public static void initialize(IWebMvcConfig config) {
		__doInitialize(config, new WebRequestProcessor());
		if (config.isI18n()) {
			I18N.setEventHandler(new II18NEventHandler() {

				@Override
				public Locale loadCurrentLocale() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void onLocaleChanged(Locale locale) {
					// TODO Auto-generated method stub
				}

				@Override
				public InputStream onLoadProperties(String resourceName) throws IOException {
					if (Cfgs.isInited()) {
						File _resourcefile = Cfgs.search("i18n/" + resourceName);
						if (_resourcefile != null) {
							return new FileInputStream(_resourcefile);
						}
					}
					return null;
				}

			});
		}
	}

	/**
	 * 销毁
	 */
	public static void destory() {
		__doDestroy();
	}

	/**
	 * @return 获取当前配置体系框架初始化配置对象
	 */
	public static IWebMvcConfig getConfig() {
		return (IWebMvcConfig) MVC.getConfig();
	}

}
