// Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
// Released under the terms of the CPL Common Public License version 1.0.
package fitnesse.html;

public class HtmlPage extends HtmlTag {
  public static final String DTD = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"; // TR/html4/strict.DTD
  public static final String BreakPoint = "<!--BREAKPOINT-->";

  private HtmlTag head;
  private HtmlTag body;
  private HtmlTag title;
  private HtmlTag sidebar;
  private HtmlTag mainbar;
  
  public HtmlTag header;
  public HtmlTag actions;
  public HtmlTag main;

  public String preDivision;
  public String postDivision;

  protected HtmlPage() {
    super("html");

    title = makeTitle();
    head = makeHead();
    body = makeBody();
    add(head);
    add(body);
  }

  public String html() {
    return DTD + endl + super.html();
  }

  protected HtmlTag makeBody() {
    HtmlTag body = new HtmlTag("body");
    mainbar = HtmlUtil.makeDivTag("mainbar");
    header = HtmlUtil.makeDivTag("header");
    sidebar = HtmlUtil.makeDivTag("sidebar");
    actions = HtmlUtil.makeDivTag("actions");
    main = HtmlUtil.makeDivTag("main");
    HtmlTag artNiche = makeArtNiche();

    mainbar.add(header);
    mainbar.add(main);

    sidebar.add(artNiche);
    sidebar.add(actions);

    body.add(sidebar);
    body.add(mainbar);

    return body;
  }

  protected HtmlTag makeArtNiche() {
    HtmlTag niche = HtmlUtil.makeAnchorTag("art_niche");
    niche.addAttribute("href", "FrontPage");
    niche.addAttribute("class", "art_niche");
    return niche;
  }

  protected HtmlTag makeHead() {
    HtmlTag head = new HtmlTag("head");
    head.add(titleTag());
    head.add(makeCssLink("/files/css/fitnesse.css", "screen"));
    head.add(makeCssLink("/files/css/fitnesse_print.css", "print"));
    head.add(HtmlUtil.makeJavascriptLink("/files/javascript/fitnesse.js"));
    return head;
  }

  private HtmlTag makeTitle() {
    HtmlTag title = new HtmlTag("title");
    title.add("FitNesse");
    return title;
  }

  protected HtmlTag titleTag() {
    return title;
  }
  
  public HtmlTag makeCssLink(String link, String media) {
    HtmlTag css = new HtmlTag("link");
    css.addAttribute("rel", "stylesheet");
    css.addAttribute("type", "text/css");
    css.addAttribute("href", link);
    css.addAttribute("media", media);
    return css;
  }

  public void setTitle(String title) {
    this.title.use(title);
  }
  
  public void divide() throws Exception {
    String html = html();
    int breakIndex = html.indexOf(BreakPoint);
    preDivision = html.substring(0, breakIndex);
    postDivision = html.substring(breakIndex + BreakPoint.length());
  }

  public void setBodyClass(String clazz) {
    body.addAttribute("class", clazz);
  }
}
