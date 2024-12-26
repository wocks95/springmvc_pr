package com.min.app11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app11.dto.BoardDto;
import com.min.app11.service.IBoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 필드로 선언된 IBoardService boardService에 자동으로 bean을 주입하기 위한 생성자입니다.
@RequestMapping(value="/board")
@Controller 
public class BoardController {
  
  private final IBoardService boardService;

  @RequestMapping(value="/list.do")
  public String list(HttpServletRequest request, Model model) {
    
    // 목록 서비스(getBoardList)로부터 결과를 받아옵니다.
    Map<String, Object> map = boardService.getBoardList(request);
    
    // JSP로 전달할 목록과 전체 목록 개수를 model에 저장합니다.
    model.addAttribute("boardList", map.get("boardList"));
    model.addAttribute("boardCount", map.get("boardCount"));
    
    return "board/list";
  }
  
  @RequestMapping(value="detail.do")
  public String detail(@RequestParam(name="boardId", required=false, defaultValue="0") int boardId, Model model) {
    
    // 상세 서비스로부터 상세 정보를 받아온 뒤 , 해당 정보를 JSP로 전달 할 수 있도록 model에 저장합니다.
    model.addAttribute("b", boardService.getBoardById(boardId));
    
    return "board/detail";
  }
  
  @RequestMapping(value="/modify.do", method=RequestMethod.POST)
  public String modify(BoardDto boardDto, RedirectAttributes redirectAttributes) {
    
    // 수정 서비스로부터 수정 결과를 받아온 뒤, 해당 정보를 리다이렉트할 수 있도록 redirectAttribute에 저장합니다.
    
    redirectAttributes.addAttribute("id", boardDto.getBoardId()) // Flash가 아닌 addAttribute에 저장한 것은 Model에 저장한 것과 동일한 상황이라는 뜻입니다.
                      .addFlashAttribute("msg", boardService.modifyBoard(boardDto));
    
    return "redirect:/board/detail.do?boardId={id}"; // 상세보기로 리다이렉트 (상세보기 요청을 작성합니다.)
                                                     // {id}는 attribute로 저장된 id를 의미합니다.
  }
  
  @RequestMapping(value="/remove.do", method=RequestMethod.POST)
  public String remove(int boardId, RedirectAttributes redirectAttributes) {
    // 삭제 서비스로부터 삭제 결과를 받아온 뒤, 해당 정보를 리다이렉트 할 수 있도록 redirectAttributes에 저장한다.
    redirectAttributes.addFlashAttribute("msg", boardService.removeBoard(boardId));
    
    return "redirect:/board/list.do";
  }
  
  @RequestMapping(value="/removes.do", method=RequestMethod.POST)
  public String removes(String[] numbers,RedirectAttributes redirectAttributes) {
   
    // 선택 삭제 서비스로부터 삭제 결과를 받아온 뒤, 해당 정보를 리다이렉트 할 수 있도록 redirectAttributes에 저장한다.
    redirectAttributes.addFlashAttribute("msg", boardService.removeBoardList(numbers));
    return "redirect:/board/list.do";
    
  }
  
  @RequestMapping(value="/write.do")
  public String write() {
    
    
    
    return "board/write";
  }
  
  @RequestMapping(value="/register.do", method=RequestMethod.POST)
  public String register(BoardDto boardDto,RedirectAttributes redirectAttributes) {
    // 삽입 서비스로부터 삭제 결과를 받아온 뒤, 해당 정보를 리다이렉트 할 수 있도록 redirectAttributes에 저장한다.
    redirectAttributes.addFlashAttribute("msg", boardService.registerBoard(boardDto));
    return "redirect:/board/list.do";
  }
  
  @RequestMapping(value="/search.do")
  public String search(HttpServletRequest request, Model model) {
    
    // 검색 서비스로부터 검색 결과 목록과 검색 결과 개수를 받아옵니다.
    Map<String, Object> map = boardService.getSearchList(request);
    
    // 검색 결과 목록과 검색 결과 개수를 JSP로 전달할 수 있도록 model에 저장합니다.
    model.addAttribute("boardList", map.get("boardList"));
    model.addAttribute("boardCount", map.get("boardCount"));
    
    return "board/list";
  }
  
  
  
}
