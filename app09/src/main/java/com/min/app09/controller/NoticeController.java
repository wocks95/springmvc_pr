package com.min.app09.controller;

import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app09.service.INoticeService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value="/notice")
@Controller
@RequiredArgsConstructor
public class NoticeController {

  private final INoticeService noticeService;
  
  @RequestMapping(value="/list.do")
  public String list(Model model) {
    model.addAttribute("noticeList", noticeService.getNoticeList());
    return "notice/list";
  }
  
  @RequestMapping(value="/write.do")
  public String write() {
    return "notice/write";
  }
  
  @RequestMapping(value="/regist.do", method=RequestMethod.POST)
  public String regist(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", noticeService.registNotice(multipartRequest));
    return "redirect:/notice/list.do";
  }
  
  @RequestMapping(value="/detail.do")
  public String detail(int noticeId, Model model) {
    Map<String, Object> map = noticeService.getNoticeById(noticeId);
    model.addAttribute("n", map.get("n"));
    model.addAttribute("attachList", map.get("attachList"));
    return "notice/detail";
  }
  
  @RequestMapping(value="/remove.do")
  public String remove(int noticeId, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", noticeService.removeNotice(noticeId));
    return "redirect:/notice/list.do";
  }
  
  @ResponseBody  // 페이지 이동 없이 응답을 처리하는 비동기 방식에서 필요합니다.
  @RequestMapping(value="/download.do")
  public ResponseEntity<Resource> download(@RequestParam(name="attachId") int attachId
                                         , @RequestHeader(name="User-Agent") String userAgent  // 요청 헤더(User-Agent : 어떤 브라우저로 접속하였는지 확인할 수 있는 헤더 값)
      ) {
    return noticeService.download(attachId, userAgent);    
  }
  
}
