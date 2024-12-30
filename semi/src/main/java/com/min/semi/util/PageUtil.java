package com.min.semi.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * 모든 목록 서비스에서 사용하는 PageUtil 클래스
 */

@Component
@Getter
public class PageUtil {

  private int page;     // 페이지 번호 (요청 파라미터, 디폴트 1)
  private int display;  // 한 페이지에 표시할 목록의 개수 (요청 파라미터, 디폴트 20)
  private int offset;   // 각 페이지마다 표시할 게시글의 시작 번호 (page와 display에 의해서 계산되는 항목)

  private int count;     // 목록의 개수 (SELECT 문 실행)
  private int pageCount; // 페이지의 개수 (total과 display에 의해서 계산되는 항목)
  private int beginPage; // 각 블록의 시작 페이지 번호 (page와 pagePerBlock에 의해서 계산되는 항목)
  private int endPage;   // 각 블록의 종료 페이지 번호 (beginPage와 pagePerBlock과 totalPage에 의해서 계산되는 항목)
  
  /**
   * 서비스로부터 page와 display와 count의 정보를 받아온 뒤<br>
   * 페이징 처리에 필요한 모든 값을 처리하는 메소드
   * @param page  현재 페이지 번호
   * @param display  현재 페이지에 표시할 목록의 개수
   * @param count  전체 목록의 개수
   */
  public void setPaging(int page, int display, int count) {
    
    this.page = page;
    this.display = display;
    offset = (page - 1) * display;
    
    int pagePerBlock = 10;    
    pageCount = (int)Math.ceil((double)count / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
    endPage = Math.min(beginPage + pagePerBlock - 1, pageCount);
    
  }
  
  /**
   * 호출한 서비스로 페이지 이동 링크를 문자열 형식으로 반환하는 메소드
   * @param requestURI  서비스가 동작하는 요청 주소
   * @param sort  정렬 방식 (ASC 또는 DESC)
   * @return  문자열 형식의 페이지 이동 링크
   */
  public String getPaging(String requestURI, String sort) {
    
    StringBuilder builder = new StringBuilder();
    
    // <style></style>
    builder.append("<style>");
    builder.append(".paging { display: flex; justify-content: center; width: 400px; margin: 0 auto; }");
    builder.append(".paging button { display: block; border: none; background-color: #fff; text-align: center; width: 30px; height: 30px; line-height: 30px; cursor: pointer; }");
    builder.append(".paging .disabled-button { color: silver; cursor: auto; }");
    builder.append(".paging .focus-page { color: limegreen; }");
    builder.append("</style>");
    
    // <div class="paging">
    builder.append("<div class=\"paging\">");
    
    // 이전 블록 <
    // 1. 링크 없음 : <button type="button" class="disabled-button">&lt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=10&sort=DESC'">&lt;</button>
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "'\">&lt;</button>");
    
    // 1  2  3  4  5  6  7  8  9  10
    // <button type="button" onclick="location.href='/app10/user/list.do?page=1&sort=DESC'" class="focus-page">1</button>
    // <button type="button" onclick="location.href='/app10/user/list.do?page=2&sort=DESC'">2</button>
    // <button type="button" onclick="location.href='/app10/user/list.do?page=3&sort=DESC'">3</button>
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&sort=" + sort + "'\" class=\"focus-page\">" + p + "</button>");
      } else {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&sort=" + sort + "'\">" + p + "</button>");
      }
    }
    
    // 다음 블록 >
    // 1. 링크 없음 : <button type="button" class="disabled-button">&gt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=11&sort=DESC'">&gt;</button>
    if(endPage == pageCount)
      builder.append("<button type=\"button\" class=\"disabled-button\">&gt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "'\">&gt;</button>");
    
    // </div>
    builder.append("</div>");
    
    return builder.toString();
    
  }
  
  /**
   * 호출한 서비스로 페이지 이동 링크를 문자열 형식으로 반환하는 메소드
   * @param requestURI  서비스가 동작하는 요청 주소
   * @param column  정렬할 칼럼
   * @param sort  정렬 방식 (ASC 또는 DESC)
   * @return  문자열 형식의 페이지 이동 링크
   */
  public String getPaging(String requestURI, String sort, String column) {
    
    StringBuilder builder = new StringBuilder();
    
    // <style></style>
    builder.append("<style>");
    builder.append(".paging { display: flex; justify-content: center; width: 400px; margin: 0 auto; }");
    builder.append(".paging button { display: block; border: none; background-color: #fff; text-align: center; width: 30px; height: 30px; line-height: 30px; cursor: pointer; }");
    builder.append(".paging .disabled-button { color: silver; cursor: auto; }");
    builder.append(".paging .focus-page { color: limegreen; }");
    builder.append("</style>");
    
    // <div class="paging">
    builder.append("<div class=\"paging\">");
    
    // 이전 블록 <
    // 1. 링크 없음 : <button type="button" class="disabled-button">&lt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=10&sort=DESC&column=title'">&lt;</button>
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "&column=" + column + "'\">&lt;</button>");
    
    // 1  2  3  4  5  6  7  8  9  10
    // <button type="button" onclick="location.href='/app10/user/list.do?page=1&sort=DESC&column=title'" class="focus-page">1</button>
    // <button type="button" onclick="location.href='/app10/user/list.do?page=2&sort=DESC&column=title'">2</button>
    // <button type="button" onclick="location.href='/app10/user/list.do?page=3&sort=DESC&column=title'">3</button>
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&sort=" + sort + "&column=" + column + "'\" class=\"focus-page\">" + p + "</button>");
      } else {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&sort=" + sort + "&column=" + column + "'\">" + p + "</button>");
      }
    }
    
    // 다음 블록 >
    // 1. 링크 없음 : <button type="button" class="disabled-button">&gt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=11&sort=DESC&column=title'">&gt;</button>
    if(endPage == pageCount)
      builder.append("<button type=\"button\" class=\"disabled-button\">&gt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "&column=" + column + "'\">&gt;</button>");
    
    // </div>
    builder.append("</div>");
    
    return builder.toString();
    
  }
  
  /**
   * 호출한 서비스로 페이지 이동 링크를 문자열 형식으로 반환하는 메소드<br>
   * 검색 화면에서 사용
   * @param requestURI  서비스가 동작하는 요청 주소
   * @param queryString  검색 조건 쿼리 스트링
   * @return  문자열 형식의 페이지 이동 링크
   */
  public String getSearchPaging(String requestURI, String queryString) {
    
    StringBuilder builder = new StringBuilder();
    
    // <style></style>
    builder.append("<style>");
    builder.append(".paging { display: flex; justify-content: center; width: 400px; margin: 0 auto; }");
    builder.append(".paging button { display: block; border: none; background-color: #fff; text-align: center; width: 30px; height: 30px; line-height: 30px; cursor: pointer; }");
    builder.append(".paging .disabled-button { color: silver; cursor: auto; }");
    builder.append(".paging .focus-page { color: limegreen; }");
    builder.append("</style>");
    
    // <div class="paging">
    builder.append("<div class=\"paging\">");
    
    // 이전 블록 <
    // 1. 링크 없음 : <button type="button" class="disabled-button">&lt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/search.do?page=10&queryString'">&lt;</button>
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (beginPage - 1) + "&" + queryString + "'\">&lt;</button>");
    
    // 1  2  3  4  5  6  7  8  9  10
    // <button type="button" onclick="location.href='/app10/user/search.do?page=1&queryString'" class="focus-page">1</button>
    // <button type="button" onclick="location.href='/app10/user/search.do?page=2&queryString'">2</button>
    // <button type="button" onclick="location.href='/app10/user/search.do?page=3&queryString'">3</button>
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&" + queryString + "'\" class=\"focus-page\">" + p + "</button>");
      } else {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&" + queryString + "'\">" + p + "</button>");
      }
    }
    
    // 다음 블록 >
    // 1. 링크 없음 : <button type="button" class="disabled-button">&gt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/search.do?page=11&queryString'">&gt;</button>
    if(endPage == pageCount)
      builder.append("<button type=\"button\" class=\"disabled-button\">&gt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (endPage + 1) + "&" + queryString + "'\">&gt;</button>");
    
    // </div>
    builder.append("</div>");
    
    return builder.toString();
    
  }
  
}
