package org.hire.me.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hire.me.exception.EncurtadorLinkException;
import org.hire.me.model.Link;
import org.hire.me.service.EncurtadorLinkService;
import org.hire.me.util.DataUtil;
import org.hire.me.vo.BaseVo;
import org.hire.me.vo.ErrorVo;
import org.hire.me.vo.LinkInputVo;
import org.hire.me.vo.LinkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("encurtar")
public class EncurtarLinkController {
	
	@Autowired
	EncurtadorLinkService service;
	
//	@RequestMapping(value="criar2", method={RequestMethod.PUT,RequestMethod.POST})
//	@ResponseBody
//	public ErrorVo criar2(LinkInputVo obj){
//		
//		System.out.println("bagteu");
//		
//		System.out.println(obj);
//		
//		return new ErrorVo("test", "", obj.toString());
//	}
	
//	@RequestMapping(value="listaRequestLinkVo", method={RequestMethod.GET})
//	@ResponseBody
//	public LinkInputVo listaRequestLinkVo(){
//		
//		LinkInputVo vo = new LinkInputVo();
//		vo.setCustomAlias("2343ewr");
//		vo.setUrl("terra.com.br");
//		
//		return vo;
//	}	

	
	@RequestMapping(value="criar", method={RequestMethod.PUT,RequestMethod.POST})
	@ResponseBody
	public BaseVo criar(LinkInputVo linkInputVo){
		try {
			long inicioTimeInMillis = DataUtil.getAgoraInMillis();
			
			System.out.println("criar / " + linkInputVo.getUrl() + " | " + linkInputVo.getCustomAlias());
			
			
			Link link = service.criar(linkInputVo.getUrl(),linkInputVo.getCustomAlias());
			
			long fimTimeInMillis = DataUtil.getAgoraInMillis();
			
			System.out.println("tempo a " + inicioTimeInMillis + " / " 
			+ fimTimeInMillis + "  = " 
					+( fimTimeInMillis - inicioTimeInMillis));
			
			return LinkVo.parse(link, ( fimTimeInMillis - inicioTimeInMillis));
			
		} catch (EncurtadorLinkException e) {
			e.printStackTrace();
			return ErrorVo.parse(linkInputVo.getCustomAlias(), e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorVo.parse(linkInputVo.getCustomAlias(), "999", e.getMessage());
		}
		
	}
	
	@RequestMapping(value="recuperar/{alias}", method={RequestMethod.GET})
	@ResponseBody
	public BaseVo recuperar(@PathVariable String alias){
		long inicioTimeInMillis = DataUtil.getAgoraInMillis();
		System.out.println("recuperar / " + alias);
		
		try {
			Link link = service.recuperar(alias);
			
			long fimTimeInMillis = DataUtil.getAgoraInMillis();
			
			return LinkVo.parseUrlOriginal(link, ( fimTimeInMillis - inicioTimeInMillis));
			
		} catch (EncurtadorLinkException e) {
			e.printStackTrace();
			return ErrorVo.parse(alias, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorVo.parse(alias, "999", e.getMessage());
		}
	}
	
	@RequestMapping(value="listar", method={RequestMethod.GET})
	@ResponseBody
	public List<Link> listar(){
		
		List<Link> links = service.listaDezMais();
		
		return links;
	}	
	
   @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String method(HttpServletResponse httpServletResponse) {
	   System.out.println("reci ");
        String projectUrl = "http://www.g1.com.br";
		httpServletResponse.setHeader("Location", projectUrl);
		return "redirect:" + projectUrl;
    }	

}
