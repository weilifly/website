package com.weili.action.front;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import com.shove.Convert;
import com.shove.web.util.JSONUtils;
import com.weili.constants.IConstants;
import com.weili.service.BrandAdvertiseService;
import com.weili.service.BrandService;
import com.weili.service.ConsumerService;
import com.weili.service.DevelopmentService;
import com.weili.service.KydReportService;
import com.weili.service.NewspaperService;
import com.weili.service.WeiliResearchesService;

public class FrontBrandAction extends BaseFrontAction {

	private static final long serialVersionUID = 1L;
	private BrandService brandService;
	private NewspaperService newspaperService;
	private WeiliResearchesService weiliResearchesService;
	private KydReportService kydReportService;
	private BrandAdvertiseService brandAdvertiseService;
	private DevelopmentService developmentService;
	private ConsumerService consumerService;
	
	private List<Map<String,Object>> brandList;
	
	public String brandDetail() throws Exception{
		List<Map<String,Object>> bList = getBrandList();
		Long bId = (Long) bList.get(0).get("id");
		Long id = Convert.strToLong(request("id"), bId);
		paramMap = brandService.queryBrandById(id);
		Integer type = Convert.strToInt(paramMap.get("type"), -1);
		Integer isLevel = Convert.strToInt(paramMap.get("isLevel"), -1);
		Long parentId = Convert.strToLong(paramMap.get("parentId"), -1);
		if(type == -1&&isLevel==IConstants.ISLEVEL_OFF&&parentId==-1){//品牌理念
			return "idea";
		}
		if(type == -1&&isLevel==IConstants.ISLEVEL_ON&&parentId==-1){//品牌文化
			List<Map<String,Object>> list = brandService.queryBrandAll("*", "1=1 and `status` = 1 and parentId = "+IConstants.BRAND_ID_CULTURE, "sortIndex asc");
			Long bandId = (Long) list.get(0).get("id");
			paramMap = brandService.queryBrandById(bandId);
			Integer mType = Convert.strToInt(paramMap.get("type"), -1);
			if(mType == 4){//华普报
				pageBean.setPageSize(12);
				int pageNum = Convert.strToInt(request("curPage"), 1);
				pageBean.setPageNum(pageNum);
				newspaperService.queryNewspaperPageFront(pageBean, IConstants.STATUS_ON);
				request("list",list);
				return "culture";
			}else if(mType == -1){//品牌文化二级详情
				request("list",list);
				return "childDetail";
			}else if(mType == 5){//微力科技报道
				List<Map<String,Object>> reportList = kydReportService.queryKydReportAll("*", "1=1 and `status` = "+IConstants.STATUS_ON, " addTime desc");
				request("list",list);
				request("reportList",reportList);
				return "report";
			}
		}
		if(type == IConstants.BRAND_TYPE_NEWS&&isLevel == IConstants.ISLEVEL_OFF&&parentId==-1){//品牌资讯
			pageBean.setPageSize(12);
			int pageNum = Convert.strToInt(request("curPage"), 1);
			pageBean.setPageNum(pageNum);
			weiliResearchesService.queryBrandNewsPageFront(pageBean, IConstants.STATUS_ON);
			Map<String,String> newMap = weiliResearchesService.queryBrandRecommendedNews(IConstants.RECOMMEND_ON);
			String regEx_html = "<[^>]+>";
			String content = newMap.get("content");
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	        Matcher m_html = p_html.matcher(content);
	        content = m_html.replaceAll(""); // 过滤html标签
	        newMap.put("content", content);
			request("newMap",newMap);
			return "news";
		}
		if(id == IConstants.BRAND_ID_NEWSPAPER){//华普报
			List<Map<String,Object>> list = brandService.queryBrandAll("*", "1=1 and `status` = 1 and parentId = "+IConstants.BRAND_ID_CULTURE, "sortIndex asc");
			pageBean.setPageSize(12);
			int pageNum = Convert.strToInt(request("curPage"), 1);
			pageBean.setPageNum(pageNum);
			newspaperService.queryNewspaperPageFront(pageBean, IConstants.STATUS_ON);

			request("list",list);
			return "culture";
		}
		if(id == IConstants.BRAND_ID_REPORT){//微力科技报道
			List<Map<String,Object>> reportList = kydReportService.queryKydReportAll("*", "1=1 and `status` = "+IConstants.STATUS_ON, " addTime desc");
			List<Map<String,Object>> list = brandService.queryBrandAll("*", "1=1 and `status` = 1 and parentId = "+IConstants.BRAND_ID_CULTURE, "sortIndex asc");
			
			request("list",list);
			request("reportList",reportList);
			return "report";
		}
		if(id == IConstants.BRAND_ID_ADVERTISE){//品牌广告
			List<Map<String,Object>> advertiseList = brandAdvertiseService.queryBrandAdvertiseAll("*", "1=1 and `status` = "+IConstants.STATUS_ON, " addTime desc");
//			List<Map<String,Object>> list = brandService.queryBrandAll("*", "1=1 and `status` = 1 and parentId = "+IConstants.BRAND_ID_CULTURE, "sortIndex asc");
			
//			request("list",list);
			request("advertiseList",advertiseList);
			return "advertise";
		}
		if(type == -1&&isLevel==IConstants.ISLEVEL_OFF&&parentId==IConstants.BRAND_ID_CULTURE){//品牌文化二级详情
			List<Map<String,Object>> list = brandService.queryBrandAll("*", "1=1 and `status` = 1 and parentId = "+IConstants.BRAND_ID_CULTURE, "sortIndex asc");
			
			request("list",list);
			return "childDetail";
		}
		
		if(type == IConstants.BRAND_TYPE_TIME&&isLevel==IConstants.ISLEVEL_OFF&&parentId==-1){//发展历程
			List<Map<String,Object>> developmentList = developmentService.queryDevelopmentAll("*", "1=1 and status = "+IConstants.STATUS_ON, "sortIndex asc");
			
			request("developmentList",developmentList);
			return "development";
		}
		
		return "idea";
	}
	
	public String brandNewsDetail() throws Exception{
		Long id = Convert.strToLong(request("id"), -1);
		
		weiliResearchesService.updateNewsViews(id);//增加浏览数
		
		Map<String,String> newsDetail = weiliResearchesService.queryWeiliResearchesById(id);
		
		paramMap = brandService.queryBrandById(IConstants.BRAND_ID_NEWS);
		
		Map<String,String> nextMap = weiliResearchesService.queryWeiliResearchesNextId(id);
		
		request("newsDetail",newsDetail);
		request("nextMap",nextMap);
		
		return SUCCESS;
	}
	
	public String kydReport() throws Exception{
		JSONObject obj = new JSONObject();
		Long id = Convert.strToLong(request("id"), -1);
		
		kydReportService.addReportViews(id);//增加浏览数
		
		obj.putAll(kydReportService.queryKydReportById(id));
		
		
		JSONUtils.printObject(obj);
		
		return null;
	}
	
	public String brandAdvertise() throws Exception{
		JSONObject obj = new JSONObject();
		Long id = Convert.strToLong(request("id"), -1);
		
		brandAdvertiseService.addAdvertiseViews(id);//增加浏览数
		
		obj.putAll(brandAdvertiseService.queryBrandAdvertiseById(id));
		
		JSONUtils.printObject(obj);
		
		return null;
	}
	
	/*
	 * sendToUs
	 * */
	public String sendToUs() throws Exception{
		JSONObject obj = new JSONObject();
		String cName = paramMap.get("cName");
		String cTelephone = paramMap.get("cTelephone");
		String address = paramMap.get("address");
		String needContent = paramMap.get("needContent");
		Integer needId = Convert.strToInt(paramMap.get("needId"), -1);
		
		long returnId =-1;
		returnId = consumerService.addConsumers(cName,cTelephone,address,needContent,needId);//增加潜在用户
		
		//obj.putAll(consumerService.addConsumers(cName,cTelephone,address,needContent,needId));//增加潜在用户
		
		obj.put("returnId", returnId);
		JSONUtils.printObject(obj);
		
		return null;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	
	
	public void setConsumerService(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	public void setNewspaperService(NewspaperService newspaperService) {
		this.newspaperService = newspaperService;
	}

	public void setWeiliResearchesService(WeiliResearchesService weiliResearchesService) {
		this.weiliResearchesService = weiliResearchesService;
	}

	public void setKydReportService(KydReportService kydReportService) {
		this.kydReportService = kydReportService;
	}

	public void setBrandAdvertiseService(BrandAdvertiseService brandAdvertiseService) {
		this.brandAdvertiseService = brandAdvertiseService;
	}

	public void setDevelopmentService(DevelopmentService developmentService) {
		this.developmentService = developmentService;
	}

	public List<Map<String, Object>> getBrandList() throws Exception {
		if(brandList == null){
			brandList = brandService.queryBrandAll("*", "1=1 and `status` = 1 and parentId = -1", "sortIndex asc");
		}
		return brandList;
	}
	
}
