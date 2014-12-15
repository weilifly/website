package com.weili.action.admin;

import com.shove.Convert;
import com.shove.util.StringCommon;
import com.shove.web.action.BasePageAction;
import com.shove.web.util.JSONUtils;
import com.weili.service.WeiliResearchesService;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WeiliResearchAction extends BasePageAction
{
  public static Log log = LogFactory.getLog(WeiliResearchAction.class);
  private WeiliResearchesService weiliResearchesService;

  public String queryWeiliResearchInit()
  {
    return "success";
  }

  public String queryWeiliResearch() throws Exception
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

    this.weiliResearchesService.queryBrandNewsPage(this.pageBean, fieldList, condition, order, table);
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
}