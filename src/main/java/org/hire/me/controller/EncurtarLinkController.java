package org.hire.me.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("encurtar")
public class EncurtarLinkController {
	
	@Autowired
	EncurtadorLinkService service;
	
	@RequestMapping(value="criar", method={RequestMethod.PUT,RequestMethod.POST})
	@ResponseBody
	public BaseVo criar(LinkInputVo linkInputVo){
		System.out.println("criar " + linkInputVo);
		try {
			long inicioTimeInMillis = DataUtil.getAgoraInMillis();
			
			Link link = service.criar(linkInputVo.getUrl(),linkInputVo.getCustomAlias());
			
			long fimTimeInMillis = DataUtil.getAgoraInMillis();
			
			return LinkVo.parse(link, ( fimTimeInMillis - inicioTimeInMillis));
			
		} catch (EncurtadorLinkException e) {
			e.printStackTrace();
			return ErrorVo.parse(linkInputVo.getCustomAlias(), e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorVo.parse(linkInputVo.getCustomAlias(), "999", e.getMessage());
		}
		
	}
	
	@RequestMapping(value="criacao", method={RequestMethod.PUT,RequestMethod.POST})
	@ResponseBody
	public BaseVo criacao(@RequestBody LinkInputVo linkInputVo){
		System.out.println("Criacao " + linkInputVo);
		try {
			long inicioTimeInMillis = DataUtil.getAgoraInMillis();
			
			Link link = service.criar(linkInputVo.getUrl(),linkInputVo.getCustomAlias());
			
			long fimTimeInMillis = DataUtil.getAgoraInMillis();
			
			return LinkVo.parse(link, ( fimTimeInMillis - inicioTimeInMillis));
			
		} catch (EncurtadorLinkException e) {
			e.printStackTrace();
			return ErrorVo.parse(linkInputVo.getCustomAlias(), e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorVo.parse(linkInputVo.getCustomAlias(), "999", e.getMessage());
		}
		
	}	
	
	@RequestMapping(value = "recuperar/{alias}", method = { RequestMethod.GET })
	@ResponseBody
	public BaseVo recuperar(@PathVariable String alias) {
		long inicioTimeInMillis = DataUtil.getAgoraInMillis();

		try {
			Link link = service.recuperar(alias);

			long fimTimeInMillis = DataUtil.getAgoraInMillis();

			return LinkVo.parseUrlOriginal(link, (fimTimeInMillis - inicioTimeInMillis));

		} catch (EncurtadorLinkException e) {
			e.printStackTrace();
			return ErrorVo.parse(alias, e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorVo.parse(alias, "999", e.getMessage());
		}
	}
	
	@RequestMapping(value = "listar", method = { RequestMethod.GET })
	@ResponseBody
	public List<Link> listar() {

		List<Link> links = service.listaDezMais();

		return links;
	}
	
	@RequestMapping(value = "remove/{alias}", method = { RequestMethod.GET })
	@ResponseBody
	public void remove(@PathVariable String alias) {	
		System.out.println("--- removendo " + alias);
		service.removeLink(alias);
		System.out.println("--- removendo sucesso ");
	}	

}
