package com.weili.action.admin;

import com.shove.Convert;
import com.shove.util.StringCommon;
import com.shove.web.action.BasePageAction;
import com.shove.web.util.JSONUtils;
import com.weili.service.ConsumerService;
import com.weili.service.WeiliResearchesService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WeiliResearchAction extends BasePageAction
{
  public static Log log = LogFactory.getLog(WeiliResearchAction.class);
  private WeiliResearchesService weiliResearchesService;
  private ConsumerService consumerService;
  
  private List<Map<String,Object>> typeList;
  private List<Map<String,Object>> needsTypeList;
  
  /**
   * 查询潜在用户初始化
   * @author weili
   * @return success
   * 2014-12-15
   * */
  public String queryConsumersInit()
  {
    return SUCCESS;
  }
  /**
   * 查询潜在用户
   * @author weili
   * @return success
   * 2014-12-15
   * */
  public String queryConsumersInfo() throws Exception
  {
    String cName = request("cName");
    String cTelephone = request("cTelephone");
    String address = request("address");
    Integer needStatus = Integer.valueOf(Convert.strToInt(request("needId"), -1));
    String needContent = request("needContent");
    String startDate = request("startDate");
    String endDate = request("endDate");
    
    String fieldList = "*";
    String order = "order by addTime desc";
    String table = "v_t_consumers_needs";

    StringBuffer condition = new StringBuffer();

    if (StringUtils.isNotBlank(cName)) {
      condition.append(" and `cName` LIKE CONCAT('%','" + cName.trim() + "','%')");
    }
    if (StringUtils.isNotBlank(cTelephone)) {
        condition.append(" and `cTelephone` LIKE CONCAT('%','" + cTelephone.trim() + "','%')");
      }
    if (StringUtils.isNotBlank(address)) {
        condition.append(" and `address` LIKE CONCAT('%','" + address.trim() + "','%')");
      }
    if (StringUtils.isNotBlank(needContent)) {
        condition.append(" and `needContent` LIKE CONCAT('%','" + needContent.trim() + "','%')");
      }
    if ((needStatus != null) && (needStatus.intValue() > 0)) {
      condition.append(" and `needStatus` = " + needStatus);
    }
    if (StringUtils.isNotBlank(startDate))
      condition.append(" and date_format(addTime,'%Y-%m-%d') >= '" + startDate + "'");
    if (StringUtils.isNotBlank(endDate)) {
      condition.append(" and date_format(addTime,'%Y-%m-%d') <= '" + endDate + "'");
    }

    this.consumerService.queryConsumersPage(this.pageBean, condition, table,order, fieldList);
    return SUCCESS;
  }
  
  /**
   * 删除潜在用户信息//局部刷新
   * @throws Exception 
   * */
 public String deleteConsumers() throws Exception{
	 JSONObject obj = new JSONObject(); 
	 String ids = request("id");
	 String msg = "删除失败！";
	 try {
			int rid = StringCommon.checkIds(ids);
			if(rid<=0){
				return null;
			}
			Long resut = consumerService.deleteConsumers(ids);
			if(resut<=0){
				return null;
			}
			msg = "1";
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			msg = "删除失败！";
		}finally{
			obj.put("msg", msg);
			JSONUtils.printObject(obj);
		}
		return null;
 }
  
 	/**
	 * 备注潜在客户初始化
	 * @return
	 * @throws Exception
	 */
	public String addRemarkInit() throws Exception {
		Long id=Convert.strToLong(request("id"), -1);
		paramMap=consumerService.queryConsumerById(id);
		return SUCCESS;
	}
	
	/**
	 * 备注潜在客户
	 * @return
	 * @throws Exception
	 */
	public String addRemark() throws Exception {
		Long id=Convert.strToLong(paramMap.get("id"), -1);
		String cName=paramMap.get("c_name");
		String cTelephone=paramMap.get("c_telephone");
		String address=paramMap.get("address");
		String remark=paramMap.get("remark");
		
		JSONObject obj = new JSONObject();

		obj.putAll(consumerService.addRemark(id, cName, cTelephone,address,remark));

		JSONUtils.printObject(obj);
		
		return null;
	}
 
 
  
  public String queryWeiliResearchInit()
  {
    return "success";
  }

  public String queryWeiliResearchInfo() throws Exception
  {
    String title = request("title");
    Integer status = Integer.valueOf(Convert.strToInt(request("status"), -1));
    String startDate = request("startDate");
    String endDate = request("endDate");

    String fieldList = "*";
    String order = "order by addTime desc";
    String table = "t_weili_research";

    StringBuffer condition = new StringBuffer();

    if (StringUtils.isNotBlank(title)) {
      condition.append(" and `title` LIKE CONCAT('%','" + title.trim() + "','%')");
    }
    if ((status != null) && (status.intValue() > 0)) {
      condition.append(" and `status` = " + status);
    }
    if (StringUtils.isNotBlank(startDate))
      condition.append(" and date_format(addTime,'%Y-%m-%d') >= '" + startDate + "'");
    if (StringUtils.isNotBlank(endDate)) {
      condition.append(" and date_format(addTime,'%Y-%m-%d') <= '" + endDate + "'");
    }

    this.weiliResearchesService.queryWeiliResearchPage(this.pageBean, fieldList, condition, order, table);
    return "success";
  }

  public String addWeiliResearchInit()
    throws Exception
  {
    List sortList = this.weiliResearchesService.querySort();
    request().setAttribute("sortList", sortList);

    return "success";
  }

  public String addWeiliResearch() throws Exception {
    String title = request("title");
    String image = request("image");
    Integer status = Integer.valueOf(Convert.strToInt(request("status"), -1));
    Integer sortIndex = Integer.valueOf(Convert.strToInt(request("sortIndex"), -1));
    Integer isRecommended = Integer.valueOf(Convert.strToInt(request("isRecommended"), -1));
    Integer isIndex = Integer.valueOf(Convert.strToInt(request("isIndex"), -1));
    String source = request("source");
    String content = request("content");
    String seoTitle = request("seoTitle");
    String seoKeywords = request("seoKeywords");
    String seoDescription = request("seoDescription");
    String addTime = request("addTime");
    Integer type = Integer.valueOf(Convert.strToInt(request("type"), -1));

    JSONObject obj = new JSONObject();

    obj.putAll(this.weiliResearchesService.addWeiliResearch(title, source, null, image, 
      content, status, isRecommended, isIndex, sortIndex, 
      seoTitle, seoKeywords, seoDescription, addTime, type));

    JSONUtils.printObject(obj);

    return null;
  }

  public String updateWeiliResearchInit()
    throws Exception
  {
    long id = Convert.strToLong(request("id"), -1L);
    this.paramMap = this.weiliResearchesService.queryWeiliResearchesById(id);
    return "success";
  }

  public String updateWeiliResearch() throws Exception {
    long id = Convert.strToLong(request("id"), -1L);
    String title = request("title");
    String image = request("image");
    String addTime = request("addTime");
    Integer status = Integer.valueOf(Convert.strToInt(request("status"), -1));
    Integer sortIndex = Integer.valueOf(Convert.strToInt(request("sortIndex"), -1));
    Integer isRecommended = Integer.valueOf(Convert.strToInt(request("isRecommended"), -1));
    Integer isIndex = Integer.valueOf(Convert.strToInt(request("isIndex"), -1));
    String source = request("source");
    String content = request("content");
    String seoTitle = request("seoTitle");
    String seoKeywords = request("seoKeywords");
    String seoDescription = request("seoDescription");

    JSONObject obj = new JSONObject();

    obj.putAll(this.weiliResearchesService.updateWeiliResearch(id, title, source, null, image, content, status, isRecommended, isIndex, sortIndex, seoTitle, seoKeywords, seoDescription, addTime));

    JSONUtils.printObject(obj);

    return null;
  }

  public String deleteWeiliResearch() throws Exception {
    JSONObject obj = new JSONObject();

    String ids = request("id");

    String msg = "删除失败！";
    try
    {
      int rid = StringCommon.checkIds(ids);
      if (rid <= 0) {
        return null;
      }

      long returnId = this.weiliResearchesService.deleteWeiliResearch(ids);
      if (returnId <= 0L) {
        return null;
      }

      msg = "1";
    }
    catch (Exception e) {
      log.error(e);
      e.printStackTrace();
      msg = "删除失败！";
    }
    finally {
      obj.put("msg", msg);
      JSONUtils.printObject(obj);
    }

    return null;
  }

  public void setWeiliResearchesService(WeiliResearchesService weiliResearchesService)
  {
    this.weiliResearchesService = weiliResearchesService;
  }
  public void setConsumerService(ConsumerService consumerService) {
	this.consumerService = consumerService;
  }
 
  public void setTypeList(List<Map<String, Object>> typeList) {
		this.typeList = typeList;
  }
  
  public void setNeedsTypeList(List<Map<String, Object>> needsTypeList) {
	this.needsTypeList = needsTypeList;
}
/**
   * 微力展示内容类型查询
   * */
  public List<Map<String, Object>> getTypeList() throws Exception{
	  if(typeList == null){
		  typeList = weiliResearchesService.queryWeiliResearchType("*","1=1");
	  }
	  return typeList;
  }
  /**
   * 用户需求类型查询
   * */
  public List<Map<String, Object>> getNeedsTypeList() throws Exception{
	  if(needsTypeList == null){
		  needsTypeList = consumerService.queryNeedsType("*","1=1");
	  }
	  return needsTypeList;
  }
  
}












