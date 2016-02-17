/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.ui.client.widget.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.aria.FocusFrame;
import com.extjs.gxt.ui.client.core.DomHelper;
import com.extjs.gxt.ui.client.core.DomQuery;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.data.SortInfo;
import com.extjs.gxt.ui.client.dnd.StatusProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ColumnHeaderEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.DragEvent;
import com.extjs.gxt.ui.client.event.DragListener;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Draggable;
import com.extjs.gxt.ui.client.util.Region;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ComponentHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Widget;

/**
 * ColumnHeader Component.
 */
public class ColumnHeader extends BoxComponent {

  public class GridSplitBar extends BoxComponent {

    protected int colIndex;
    protected Draggable d;
    protected boolean dragging;
    protected DragListener listener = new DragListener() {

      @Override
      public void dragEnd(DragEvent de) {
        onDragEnd(de);
      }

      @Override
      public void dragStart(DragEvent de) {
        onDragStart(de);
      }

    };
    protected int startX;

    protected void onDragEnd(DragEvent e) {
      dragging = false;
      headerDisabled = false;
      el().setStyleAttribute("opacity", "0");
      el().setWidth(splitterWidth);
      bar.el().setVisibility(false);

      int endX = e.getX();
			int diff;
			if (LocaleInfo.getCurrentLocale().isRTL()) {
				setStyleAttribute("borderRight", "none");
				diff = startX - endX;
			} else {
				setStyleAttribute("borderLeft", "none");
				diff = endX - startX;
			}
      onColumnSplitterMoved(colIndex, cm.getColumnWidth(colIndex) + diff);
    }

    protected void onDragStart(DragEvent e) {
      headerDisabled = true;
      dragging = true;
			if (LocaleInfo.getCurrentLocale().isRTL()) {
        setStyleAttribute("borderRight", "1px solid black");
      } else {
        setStyleAttribute("borderLeft", "1px solid black");
      }
      setStyleAttribute("cursor", "default");
      el().setStyleAttribute("opacity", "1");
      el().setWidth(1);

      startX = e.getX();

      int cols = cm.getColumnCount();
      for (int i = 0, len = cols; i < len; i++) {
        if (cm.isHidden(i) || !cm.isResizable(i)) continue;
        Element hd = getHead(i).getElement();
        if (hd != null) {
          Region rr = El.fly(hd).getRegion();
          if (LocaleInfo.getCurrentLocale().isRTL()) {
            if (startX < rr.left + 5 && startX > rr.left - 5) {
              colIndex = heads.indexOf(getHead(i));
              if (colIndex != -1) {
                break;
              }
            }
          } else {
            if (startX > rr.right - 5 && startX < rr.right + 5) {
              colIndex = heads.indexOf(getHead(i));
              if (colIndex != -1) {
                break;
              }
            }
          }
        }
      }
      if (colIndex > -1) {
        Element c = getHead(colIndex).getElement();
        int x = startX;
				int minx, maxx;
				if (LocaleInfo.getCurrentLocale().isRTL()) {
					maxx = fly((com.google.gwt.user.client.Element) c).getWidth() - minColumnWidth;
					minx = (container.el().getX() + container.el().getWidth()) - e.getEvent().getClientX();
				} else {
					minx = x - fly((com.google.gwt.user.client.Element) c).getX() - minColumnWidth;
					maxx = (container.el().getX() + container.el().getWidth()) - e.getEvent().getClientX();
				}
        d.setXConstraint(minx, maxx);
      }
    }

    protected void onMouseMove(Head header, ComponentEvent ce) {
      int activeHdIndex = heads.indexOf(header);

      if (dragging || !header.config.isResizable()) {
        return;
      }

      // find the previous column which is not hidden
      int before = -1;
      for (int i = activeHdIndex - 1; i >= 0; i--) {
        if (!cm.isHidden(i)) {
          before = i;
          break;
        }
      }
      Event event = ce.getEvent();
      int x = event.getClientX();
      Region r = header.el().getRegion();
      int hw = splitterWidth;

      el().setY(container.el().getY());
      el().setHeight(container.getHeight());

      Style ss = getElement().getStyle();

      if (x - r.left <= hw && before != -1 && cm.isResizable(before) && !cm.isFixed(before)) {
        bar.el().setVisibility(true);
        el().setX(r.left - (hw / 2));
        ss.setProperty("cursor", GXT.isSafari ? "e-resize" : "col-resize");
      } else if (r.right - x <= hw && cm.isResizable(activeHdIndex) && !cm.isFixed(activeHdIndex)) {
        bar.el().setVisibility(true);
        el().setX(r.right - (hw / 2));
        ss.setProperty("cursor", GXT.isSafari ? "w-resize" : "col-resize");
      } else {
        bar.el().setVisibility(false);
        ss.setProperty("cursor", "");
      }
    }

    @Override
    protected void onRender(com.google.gwt.user.client.Element target, int index) {
      super.onRender(target, index);
      setElement(DOM.createDiv(), target, index);

      if (GXT.isOpera) {
        el().setStyleAttribute("cursor", "w-resize");
      } else {
        el().setStyleAttribute("cursor", "col-resize");
      }
      setStyleAttribute("position", "absolute");
      setWidth(5);

      el().setVisibility(false);
      el().setStyleAttribute("backgroundColor", "white");
      el().setStyleAttribute("opacity", "0");

      d = new Draggable(this);
      d.setUseProxy(false);
      d.setConstrainVertical(true);
      d.setStartDragDistance(0);
      d.addDragListener(listener);
    }
  }

  public class Group extends BoxComponent {

    protected HeaderGroupConfig config;

    public Group(HeaderGroupConfig config) {
      this.config = config;
      config.group = this;
      groups.add(this);
    }

    public void setHtml(String html) {
      el().setInnerHtml(html);
    }

    @Override
    protected void doAttachChildren() {
      ComponentHelper.doAttach(config.getWidget());
    }

    @Override
    protected void doDetachChildren() {
      ComponentHelper.doDetach(config.getWidget());
    }

    @Override
    protected void onRender(Element target, int index) {
      setElement(DOM.createDiv(), target, index);
      setStyleName("x-grid3-hd-inner");

      if (config.getWidget() != null) {
        el().appendChild(config.getWidget().getElement());
      } else {
        el().setInnerHtml(config.getHtml());
      }
    }
  }

  public class Head extends BoxComponent {

    protected int column;
    protected ColumnConfig config;

    private AnchorElement btn;
    private ImageElement img;
    private int row;
    private Html text;

    private Widget widget;

    public Head(ColumnConfig column) {
      this.config = column;
      this.column = cm.indexOf(column);
      baseStyle = "x-grid3-hd-inner x-grid3-hd-" + column.getId();
      if (column.getColumnStyleName() != null) {
        baseStyle += " " + column.getColumnStyleName();
      }
      heads.add(this);

      render(DOM.createDiv());

      getElement().setAttribute("x-col", column.getId());
    }

    public void activateTrigger(boolean activate) {
      El e = el().findParent("td", 3);
      if (e != null) {
        e.setStyleName("x-grid3-hd-menu-open", activate);
      }
    }

    public Element getTrigger() {
      return (Element) btn.cast();
    }

    @Override
    public void onComponentEvent(ComponentEvent ce) {
      super.onComponentEvent(ce);

      int type = ce.getEventTypeInt();
      switch (type) {
        case Event.ONMOUSEOVER:
          onMouseOver(ce);
          break;
        case Event.ONMOUSEOUT:
          onMouseOut(ce);
          break;
        case Event.ONMOUSEMOVE:
          onMouseMove(ce);
          break;
        case Event.ONMOUSEDOWN:
          onHeaderMouseDown(ce, cm.indexOf(config));
          break;
        case Event.ONCLICK:
          onClick(ce);
          break;
        case Event.ONDBLCLICK:
          onDoubleClick(ce);
          break;
        case Event.ONBLUR:
          FocusFrame.get().unframe();
          break;
        case Event.ONKEYPRESS:
          onKeyPress(ce);
          break;
      }
    }

    public void setHeaderHtml(String header) {
      if (text != null) text.setHtml(header);
    }

    public void setHeaderText(String text) {
      setHeaderHtml(El.toSafeHTML(text));
    }

    public void updateWidth(int width) {
      if (!config.isHidden()) {
        El td = el().findParent("td", 3);
        td.setWidth(width);
        el().setWidth(width - td.getFrameWidth("lr"), true);

        Element th = getTableHeader(column);
        th.getStyle().setPropertyPx("width", width);
      }
    }

    protected void activate() {
      if (!cm.isMenuDisabled(indexOf(this))) {
        El td = el().findParent("td", 3);
        td.addStyleName("x-grid3-hd-over");
        int h = td.getHeight(true);
        el().setHeight(h, true);
        if (btn != null) {
          El.fly(btn).setHeight(h, true);
        }
      }
    }

    protected void deactivate() {
      if (isRendered()) {
        el().findParent("td", 3).removeStyleName("x-grid3-hd-over");
      }
    }

    @Override
    protected void doAttachChildren() {
      super.doAttachChildren();
      ComponentHelper.doAttach(widget);
    }

    @Override
    protected void doDetachChildren() {
      super.doDetachChildren();
      ComponentHelper.doDetach(widget);
    }

    protected void onKeyPress(ComponentEvent ce) {
      if (GXT.isFocusManagerEnabled() && ce.getKeyCode() == 32) {
        onHeaderClick(ce, column);
      }
    }

    @Override
    protected void onRender(Element target, int index) {
      setElement(DOM.createDiv(), target, index);

      btn = Document.get().createAnchorElement();
      btn.setHref("#");
      btn.setClassName("x-grid3-hd-btn");

      img = Document.get().createImageElement();
      img.setSrc(GXT.BLANK_IMAGE_URL);
      img.setClassName("x-grid3-sort-icon");

      el().dom.appendChild(btn);

      if (config.getWidget() != null) {
        Element span = Document.get().createSpanElement().cast();
        widget = config.getWidget();
        span.appendChild(widget.getElement());
        getElement().appendChild(span);
      } else {
        text = new Html(config.getHeaderHtml());
        text.setTagName("span");
        text.render(el().dom);
      }

      el().dom.appendChild(img);

      String tip = config.getToolTip();
      if (tip != null) {
        getElement().setAttribute("qtip", tip);
      }

      setAriaRole(config.ariaIgnore ? "presentation" : "columnheader");
      setAriaState("aria-haspopup", "true");
      setAriaState("aria-owns", getId() + "-menu");

      sinkEvents(Event.ONCLICK | Event.ONDBLCLICK | Event.MOUSEEVENTS | Event.FOCUSEVENTS | Event.ONKEYPRESS);
    }

    private Element getTableHeader(int columnIndex) {
      int domIndex = getDomIndexByColumn(columnIndex);

      NodeList<Element> ths = getTableHeads();

      if (ths.getLength() > domIndex) {
        return ths.getItem(domIndex);
      }

      return null;
    }

    private NodeList<Element> getTableHeads() {
      return tbody.getFirstChildElement().getChildNodes().cast();
    }

    private void onClick(ComponentEvent ce) {
      ce.preventDefault();
      if (ce.getTarget() == (Element) btn.cast()) {
        onDropDownClick(ce, column);
      } else {
        onHeaderClick(ce, column);
      }
    }

    private void onDoubleClick(ComponentEvent ce) {
      onHeaderDoubleClick(ce, column);
    }

    private void onMouseMove(ComponentEvent ce) {
      if (bar != null) bar.onMouseMove(this, ce);
    }

    private void onMouseOut(ComponentEvent ce) {
      deactivate();
    }

    private void onMouseOver(ComponentEvent ce) {
      if (headerDisabled) {
        return;
      }
      activate();
    }
  }

  protected GridSplitBar bar;
  protected ColumnModel cm;
  protected BoxComponent container;
  protected List<Group> groups = new ArrayList<Group>();
  protected boolean headerDisabled;
  protected List<Head> heads = new ArrayList<Head>();
  protected Menu menu;
  protected int minColumnWidth = 10;
  protected Draggable reorderer;
  protected int rows;
  protected int splitterWidth = 5;
  protected FlexTable table = new FlexTable();
  private QuickTip quickTip;;

  /**
   * Maps actual column indexes to the TABLE TH and TD index.
   */
  protected int[] columnToHead;

  private Element tbody = Document.get().createTBodyElement().cast();

  /**
   * Creates a new column header.
   * 
   * @param container the containing component
   * @param cm the column model
   */
  public ColumnHeader(BoxComponent container, ColumnModel cm) {
    this.container = container;
    this.cm = cm;
		if(GXT.isChrome && LocaleInfo.getCurrentLocale().isRTL()) {
			setStyleAttribute("float","left");
		}
    disableTextSelection(true);
  }

  /**
   * Returns the header's container component.
   * 
   * @return the container component
   */
  public BoxComponent getContainer() {
    return container;
  }

  @Override
  public Element getElement() {
    // we need this because of lazy rendering
    return table.getElement();
  }

  public Head getHead(int column) {
    return (column > -1 && column < heads.size()) ? heads.get(column) : null;
  }

  /**
   * Returns the minimum column width.
   * 
   * @return the column width
   */
  public int getMinColumnWidth() {
    return minColumnWidth;
  }

  /**
   * Returns the splitter width.
   * 
   * @return the splitter width in pixels.
   */
  public int getSplitterWidth() {
    return splitterWidth;
  }

  /**
   * Returns the index of the given column head.
   * 
   * @param head the column head
   * @return the index
   */
  public int indexOf(Head head) {
    return heads.indexOf(head);
  }

  @Override
  public boolean isAttached() {
    if (table != null) {
      return table.isAttached();
    }
    return false;
  }

  @Override
  public void onBrowserEvent(Event event) {
    super.onBrowserEvent(event);

    // Delegate events to the widget.
    table.onBrowserEvent(event);
  }

  @SuppressWarnings("rawtypes")
  public void refresh() {
    groups.clear();
    heads.clear();

    columnToHead = new int[cm.getColumnCount()];
    for (int i = 0, mark = 0; i < columnToHead.length; i++) {
      columnToHead[i] = cm.isHidden(i) ? -1 : mark++;
    }

    int cnt = table.getRowCount();
    for (int i = 0; i < cnt; i++) {
      table.removeRow(0);
    }

    table.setWidth(cm.getTotalWidth() + "px");

    Element body = fly(table.getElement()).selectNode("tbody").dom;

    table.getElement().insertBefore(tbody, body);
    removeChildren(tbody);
    DomHelper.insertHtml("afterBegin", tbody, renderHiddenHeaders(getColumnWidths()));

    List<HeaderGroupConfig> configs = cm.getHeaderGroups();

    FlexCellFormatter cf = table.getFlexCellFormatter();
    RowFormatter rf = table.getRowFormatter();

    rows = 0;
    for (HeaderGroupConfig config : configs) {
      rows = Math.max(rows, config.getRow() + 1);
    }
    rows += 1;

    for (int i = 0; i < rows; i++) {
      rf.setStyleName(i, "x-grid3-hd-row");
      rf.getElement(i).setAttribute("role", "presentation");
    }

    int cols = cm.getColumnCount();

    String cellClass = "x-grid3-header" + " " + "x-grid3-hd";

    if (rows > 1) {
      Map<Integer, Integer> map = new HashMap<Integer, Integer>();
      for (int i = 0; i < rows - 1; i++) {
        for (HeaderGroupConfig config : cm.getHeaderGroups()) {
          int col = config.getColumn();
          int row = config.getRow();
          Integer start = map.get(row);

          if (start == null || col < start) {
            map.put(row, col);
          }
        }
      }
    }

    // groups
    for (HeaderGroupConfig config : cm.getHeaderGroups()) {
      int col = config.getColumn();
      int row = config.getRow();
      int rs = config.getRowspan();
      int cs = config.getColspan();

      Group group = createNewGroup(config);

      boolean hide = true;
      if (rows > 1) {
        for (int i = col; i < (col + cs); i++) {
          if (!cm.isHidden(i)) {
            hide = false;
          }
        }
      }
      if (hide) {
        continue;
      }

      table.setWidget(row, col, group);

      cf.setStyleName(row, col, cellClass);

      HorizontalAlignment align = config.getHorizontalAlignment();
      if (align == HorizontalAlignment.RIGHT) {
        cf.setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_RIGHT);
      } else if (align == HorizontalAlignment.LEFT) {
        cf.setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_LEFT);
      } else {
        cf.setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_CENTER);
      }

      int ncs = cs;
      if (cs > 1) {
        for (int i = col; i < (col + cs); i++) {
          if (cm.isHidden(i)) {
            ncs -= 1;
          }
        }
      }

      cf.setRowSpan(row, col, rs);
      cf.setColSpan(row, col, ncs);
    }

    for (int i = 0; i < cols; i++) {
      Head h = createNewHead(cm.getColumn(i));
      if (cm.isHidden(i)) {
        continue;
      }
      int rowspan = 1;
      if (rows > 1) {
        for (int j = rows - 2; j >= 0; j--) {
          if (!cm.hasGroup(j, i)) {
            rowspan += 1;
          }
        }
      }

      int row;
      if (rowspan > 1) {
        row = (rows - 1) - (rowspan - 1);
      } else {
        row = rows - 1;
      }

      h.row = row;

      if (rowspan > 1) {
        table.setWidget(row, i, h);
        table.getFlexCellFormatter().setRowSpan(row, i, rowspan);
      } else {
        table.setWidget(row, i, h);
      }

      cf.setStyleName(row, i, "x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-" + cm.getColumnId(i));
      cf.getElement(row, i).setAttribute("role", "presentation");
      cf.getElement(row, i).setPropertyInt("gridColumnIndex", i);

      cf.setStyleName(row, i, "x-grid3-header x-grid3-hd x-grid3-cell x-grid3-td-" + cm.getColumnId(i));

			HorizontalAlignment align = com.extjs.gxt.ui.client.Style
					.convertHorizontalAlignmentToStrict(cm.getColumnAlignment(i));
			boolean isRTL = LocaleInfo.getCurrentLocale().isRTL();
      if (align == HorizontalAlignment.RIGHT) {
        table.getCellFormatter().setHorizontalAlignment(row, i, HasHorizontalAlignment.ALIGN_RIGHT);
				if(!isRTL) {
          table.getCellFormatter().getElement(row, i).getFirstChildElement().getStyle().setPropertyPx("paddingRight", 16);
				}
      } else if (align == HorizontalAlignment.CENTER) {
        table.getCellFormatter().setHorizontalAlignment(row, i, HasHorizontalAlignment.ALIGN_CENTER);
      } else {
        table.getCellFormatter().setHorizontalAlignment(row, i, HasHorizontalAlignment.ALIGN_LEFT);
				if(isRTL) {
					table.getCellFormatter().getElement(row, i).getFirstChildElement().getStyle().setPropertyPx("paddingLeft", 16);
				}
      }

    }

    if (container instanceof Grid) {
      Grid<?> grid = (Grid) container;
      SortInfo sortInfo = grid.getStore().getSortState();
      if (sortInfo != null && sortInfo.getSortField() != null) {
        updateSortIcon(grid.getColumnModel().findColumnIndex(sortInfo.getSortField()), sortInfo.getSortDir());
      }
    }

    cleanCells();

    adjustColumnWidths(getColumnWidths());

    if (isAttached()) {
      adjustHeights();
    }
  }

  /**
   * Do not call.
   */
  public void release() {
    ComponentHelper.doDetach(this);
    if (bar != null && bar.isRendered()) {
      bar.el().remove();
    }
  }

  public void setEnableColumnReorder(boolean enable) {
    if (enable && reorderer == null) {
      reorderer = new Draggable(this);
      reorderer.setUseProxy(true);
      reorderer.setSizeProxyToSource(false);
      reorderer.setMoveAfterProxyDrag(false);
      reorderer.setProxy(StatusProxy.get().el());
      reorderer.addDragListener(new DragListener() {

        private Head active;
        private int newIndex = -1;
        private Head start;
        private El statusIndicatorBottom;
        private El statusIndicatorTop;

        private StatusProxy statusProxy = StatusProxy.get();

        public void dragCancel(DragEvent de) {
          afterDragEnd();
        }

        public void dragEnd(DragEvent de) {
          if (statusProxy.getStatus()) {
            cm.moveColumn(start.column, newIndex);
          }
          afterDragEnd();
        }

        public void dragMove(DragEvent de) {
          de.setX(de.getClientX() + 12 + XDOM.getBodyScrollLeft());
          de.setY(de.getClientY() + 12 + XDOM.getBodyScrollTop());

          Head h = ComponentManager.get().find(adjustTargetElement(de.getTarget()), Head.class);

          if (h != null && !h.equals(start)) {
            HeaderGroupConfig g = cm.getGroup(h.row - 1, h.column);
            HeaderGroupConfig s = cm.getGroup(start.row - 1, start.column);
            if ((g == null && s == null) || (g != null && g.equals(s))) {
              active = h;
							boolean before;
							boolean isRTL = LocaleInfo.getCurrentLocale().isRTL();
							if(isRTL) {
								before = de.getClientX() > active.getAbsoluteLeft() + active.getWidth() / 2;
							} else {
								before = de.getClientX() < active.getAbsoluteLeft() + active.getWidth() / 2;
							}
              showStatusIndicator(true);

							char beforeAlign = isRTL ? 'r' : 'l';
							char afterAlign = isRTL ? 'l' : 'r';
              if (before) {
								statusIndicatorTop.alignTo(active.el().dom, "b-t" + beforeAlign, new int[] {-1, 0});
								statusIndicatorBottom.alignTo(active.el().dom, "t-b" + beforeAlign, new int[] {-1, 0});
              } else {
								statusIndicatorTop.alignTo(active.el().dom, "b-t" + afterAlign, new int[] {1, 0});
								statusIndicatorBottom.alignTo(active.el().dom, "t-b" + afterAlign, new int[] {1, 0});
              }

              int i = active.column;
              if (!before) {
                i++;
              }

              int aIndex = i;

              if (start.column < active.column) {
                aIndex--;
              }
              newIndex = i;
              if (aIndex != start.column) {
                statusProxy.setStatus(true);
              } else {
                showStatusIndicator(false);
                statusProxy.setStatus(false);
              }
            } else {
              active = null;
              showStatusIndicator(false);
              statusProxy.setStatus(false);
            }

          } else {
            active = null;
            showStatusIndicator(false);
            statusProxy.setStatus(false);
          }
        }

        public void dragStart(DragEvent de) {
          Head h = ComponentManager.get().find(de.getTarget(), Head.class);
          if (h != null && !h.config.isFixed()) {
            headerDisabled = true;
            quickTip.disable();
            if (bar != null) {
              bar.hide();
            }

            if (statusIndicatorBottom == null) {
              statusIndicatorBottom = new El(DOM.createDiv());
              statusIndicatorBottom.addStyleName("col-move-bottom");
              statusIndicatorTop = new El(DOM.createDiv());
              statusIndicatorTop.addStyleName("col-move-top");
            }

            XDOM.getBody().appendChild(statusIndicatorTop.dom);
            XDOM.getBody().appendChild(statusIndicatorBottom.dom);

            start = h;
            statusProxy.setStatus(false);
            statusProxy.update(start.config.getHeaderHtml());
          } else {
            de.setCancelled(true);
          }

        }

        private Element adjustTargetElement(Element target) {
          return (Element) (target.getFirstChildElement() != null ? target.getFirstChildElement() : target);
        }

        private void afterDragEnd() {
          start = null;
          active = null;
          newIndex = -1;
          removeStatusIndicator();

          headerDisabled = false;

          if (bar != null) {
            bar.show();
          }

          quickTip.enable();
        }

        private void removeStatusIndicator() {
          if (statusIndicatorBottom != null) {
            statusIndicatorBottom.remove();
            statusIndicatorTop.remove();
          }
        }

        private void showStatusIndicator(boolean show) {
          if (statusIndicatorBottom != null) {
            statusIndicatorBottom.setVisibility(show);
            statusIndicatorTop.setVisibility(show);
          }
        }
      });
    } else if (reorderer != null && !enable) {
      reorderer.release();
      reorderer = null;
    }
  }

  /**
   * True to enable column resizing.
   * 
   * @param enable true to enable, otherwise false
   */
  public void setEnableColumnResizing(boolean enable) {
    if (bar == null && enable) {
      bar = new GridSplitBar();
      bar.render(container.getElement());
      if (isAttached()) {
        ComponentHelper.doAttach(bar);
      }
      bar.show();
    } else if (bar != null && !enable) {
      ComponentHelper.doDetach(bar);
      bar.el().remove();
      bar = null;
    }
  }

  /**
   * Sets the column's header HTML.
   * 
   * @param column the column index
   * @param headerHtml the header text as HTML
   */
  public void setHeaderHtml(int column, String headerHtml) {
    getHead(column).setHeaderHtml(headerHtml);
  }

  /**
   * Sets the column's header text.
   * 
   * @param column the column index
   * @param text the header text
   */
  public void setHeaderText(int column, String text) {
    getHead(column).setHeaderText(text);
  }

  /**
   * Sets the header's context menu.
   * 
   * @param menu the context menu
   */
  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  /**
   * Sets the minimum column width.
   * 
   * @param minColumnWidth the minimum column width
   */
  public void setMinColumnWidth(int minColumnWidth) {
    this.minColumnWidth = minColumnWidth;
  }

  /**
   * Sets the splitter width.
   * 
   * @param splitterWidth the splitter width
   */
  public void setSplitterWidth(int splitterWidth) {
    this.splitterWidth = splitterWidth;
  }

  /**
   * Shows the column's header context menu.
   * 
   * @param column the column index
   */
  public void showColumnMenu(final int column) {
    menu = getContextMenu(column);

    ComponentEvent ge = createColumnEvent(this, column, menu);
    if (!container.fireEvent(Events.HeaderContextMenu, ge)) {
      return;
    }
    if (menu != null) {
      final Head h = getHead(column);
      menu.setId(h.getId() + "-menu");
      h.activateTrigger(true);
      menu.addListener(Events.Hide, new Listener<BaseEvent>() {
        public void handleEvent(BaseEvent be) {
          h.activateTrigger(false);
          container.focus();
          if (GXT.isFocusManagerEnabled()) {
            selectHeader(column);
          }
        }
      });
			if (LocaleInfo.getCurrentLocale().isRTL()) {
        menu.show(h.getTrigger(), "tr-br?");
      } else {
        menu.show(h.getTrigger(), "tl-bl?");
      }
    }
  }

  @Override
  public void sinkEvents(int eventBitsToAdd) {
    table.sinkEvents(eventBitsToAdd);
  }

  public void updateAllColumnWidths() {
    adjustColumnWidths(getColumnWidths());
  }

  public void updateColumnHidden(int index, boolean hidden) {
    refresh();
    cleanCells();
  }
  
  public void updateColumnWidth(int column, int width) {
    if (groups != null && groups.size() > 0) {
      adjustColumnWidths(getColumnWidths());
      return;
    }
    Head h = getHead(column);
    if (h != null) {
      h.updateWidth(width);
    }
  }

  public void updateSortIcon(int colIndex, SortDir dir) {
    for (int i = 0; i < heads.size(); i++) {
      Head h = heads.get(i);
      if (h.isRendered()) {
        if (i == colIndex && dir != SortDir.NONE) {
          h.addStyleName(dir == SortDir.DESC ? "sort-desc" : "sort-asc");
          h.removeStyleName(dir != SortDir.DESC ? "sort-desc" : "sort-asc");
          h.el().setElementAttribute("aria-sort", dir != SortDir.DESC ? "descending" : "ascending");
          // fixes issue with IE initially hiding sort icon on change
          h.el().repaint();
        } else {
          h.el().removeStyleName("sort-asc", "sort-desc");
          h.el().setElementAttribute("aria-sort", "none");
        }
      }
    }
  }

  public void updateTotalWidth(int offset, int width) {
    if (offset != -1) table.getElement().getParentElement().getStyle().setPropertyPx("width", ++offset);
    table.getElement().getStyle().setProperty("width", (++width) + "px");
  }

  protected void adjustCellWidth(Element cell, int width) {
    cell.getStyle().setPropertyPx("width", width);
    int adj = fly(cell).getFrameWidth("lr");
    Element inner = cell.getFirstChildElement().cast();

    fly(inner).setWidth(width - adj, true);
    if (isAttached()) {
      int before = cell.getOffsetHeight();
      fly(inner).setHeight(before, true);
      int after = cell.getOffsetHeight();
      // getting different values when some browsers are zoomed which is
      // causing the column heights to grow
      if (after != before) {
        fly(inner).setHeight(before + (before - after), true);
      }
    }
  }

  protected void adjustColumnWidths(int[] columnWidths) {
    NodeList<Element> ths = tbody.getFirstChildElement().getChildNodes().cast();
    if (ths == null) {
      return;
    }

    for (int i = 0; i < columnWidths.length; i++) {
      if (cm.isHidden(i)) {
        continue;
      }

      ths.getItem(getDomIndexByColumn(i)).getStyle().setPropertyPx("width", columnWidths[i]);
    }

    cleanCells();

    for (int i = 0; i < heads.size(); i++) {
      Head head = heads.get(i);
      if (head != null && !head.isRendered()) continue;
      String id = head.getElement().getAttribute("x-col");

      ColumnConfig cc = cm.getColumnById(id);
      if (cc == null) return;
      int w = cc.getWidth();
      Element cell = head.getElement().getParentElement().cast();
      adjustCellWidth(cell, w);
    }

    for (int i = 0; i < groups.size(); i++) {
      Group group = groups.get(i);
      if (group != null && !group.isRendered()) continue;
      Element cell = group.getElement().getParentElement().cast();
      int colspan = 1;
      String scolspan = cell.getAttribute("colspan");
      if (scolspan != null && !scolspan.equals("")) {
        colspan = Integer.parseInt(scolspan);
      }
      int w = 0;
      int mark = group.config.getColumn();
      for (int k = mark; k < (mark + colspan); k++) {
        ColumnConfig c = cm.getColumn(k);
        if (c.isHidden()) {
          mark++;
          continue;
        }
        w += cm.getColumnWidth(k);
      }
      mark += colspan;

      adjustCellWidth(cell, w);
    }
  }

  protected void adjustHeights() {
    for (Head head : heads) {
      if (head.isRendered()) {
        int h = head.el().getParent().getHeight();
        if (h > 0) {
          head.setHeight(h);
        }
      }
    }
  }

  protected void cleanCells() {
    NodeList<Element> tds = DomQuery.select("tr.x-grid3-hd-row > td", table.getElement());
    for (int i = 0; i < tds.getLength(); i++) {
      Element td = tds.getItem(i);
      if (!td.hasChildNodes()) {
        El.fly(td).removeFromParent();
      }
    }
  }

  protected ComponentEvent createColumnEvent(ColumnHeader header, int column, Menu menu) {
    return new ColumnHeaderEvent(header, container, column, menu);
  }

  protected Group createNewGroup(HeaderGroupConfig config) {
    return new Group(config);
  }

  protected Head createNewHead(ColumnConfig config) {
    return new Head(config);
  }

  @Override
  protected void doAttachChildren() {
    super.doAttachChildren();
    ComponentHelper.doAttach(bar);
  }

  @Override
  protected void doDetachChildren() {
    super.doDetachChildren();
    ComponentHelper.doDetach(bar);
  }

  protected int getColumnIndexByDom(int domIndex) {
    assert columnToHead != null && domIndex < columnToHead.length;
    for (int i = 0; i < columnToHead.length; i++) {
      if (columnToHead[i] == domIndex) {
        return i;
      }
    }

    return -1;

  }

  protected int[] getColumnWidths() {
    int colCount = cm.getColumnCount();
    int[] columnWidths = new int[colCount];
    for (int i = 0; i < colCount; i++) {
      columnWidths[i] = cm.getColumnWidth(i);
    }
    return columnWidths;
  }

  protected int getColumnWidths(int start, int end) {
    int w = 0;
    for (int i = start; i < end; i++) {
      if (!cm.isHidden(i)) {
        w += cm.getColumnWidth(i);
      }
    }
    return w;
  }

  protected Menu getContextMenu(int column) {
    return menu;
  }

  protected int getDomIndexByColumn(int column) {
    assert columnToHead != null && column < columnToHead.length;
    return columnToHead[column];
  }

  @Override
  protected void onAttach() {
    ComponentHelper.doAttach(table);
    DOM.setEventListener(getElement(), this);
    doAttachChildren();
    onLoad();
    adjustHeights();
  }

  protected void onColumnSplitterMoved(int colIndex, int width) {

  }

  @Override
  protected void onDetach() {
    try {
      onUnload();
    } finally {
      ComponentHelper.doDetach(table);
      doDetachChildren();
    }
    onDetachHelper();
  }

  protected void onDropDownClick(ComponentEvent ce, int column) {
    ce.cancelBubble();
    ce.preventDefault();
    showColumnMenu(column);
  }

  protected void onHeaderClick(ComponentEvent ce, int column) {
    ComponentEvent evt = createColumnEvent(this, column, menu);
    evt.setEvent(ce.getEvent());
    container.fireEvent(Events.HeaderClick, evt);
  }

  protected void onHeaderDoubleClick(ComponentEvent ce, int column) {
    ComponentEvent evt = createColumnEvent(this, column, menu);
    evt.setEvent(ce.getEvent());
    container.fireEvent(Events.HeaderDoubleClick, evt);
  }

  protected void onHeaderMouseDown(ComponentEvent ce, int column) {
    ComponentEvent evt = createColumnEvent(this, column, menu);
    evt.setEvent(ce.getEvent());
    container.fireEvent(Events.HeaderMouseDown, evt);
  }

  protected void onKeyDown(ComponentEvent ce, int index) {

  }

  @Override
  protected void onRender(Element target, int index) {
    table.setCellPadding(0);
    table.setCellSpacing(0);
    table.getElement().getStyle().setProperty("tableLayout", "fixed");
    table.getElement().setAttribute("role", "presentation");
    table.getElement().getFirstChildElement().setAttribute("role", "presentation");
    setElement(table.getElement(), target, index);

    List<HeaderGroupConfig> configs = cm.getHeaderGroups();
    rows = 0;
    for (HeaderGroupConfig config : configs) {
      rows = Math.max(rows, config.getRow() + 1);
    }
    rows++;

    quickTip = new QuickTip(this);

    refresh();
  }

  protected String renderHiddenHeaders(int[] columnWidths) {
    StringBuffer heads = new StringBuffer();

    heads.append("<tr>");
    for (int i = 0; i < columnWidths.length; i++) {
      // unlike GridView, we do NOT render TH's for hidden elements because of
      // support of
      // rowspan and colspan with header configs
      if (cm.isHidden(i)) {
        continue;
      }

      String styles = "height: 0px; width: " + columnWidths[i] + "px;";
      heads.append("<th style=\"" + styles + "\"></th>");
    }

    heads.append("</tr>");

    return heads.toString();
  }

  protected void selectHeader(int index) {
    Head h = getHead(index);
    if (h != null && h.isVisible()) {
      for (Head head : heads) {
        head.removeStyleName("x-column-header-sel");
        head.deactivate();
      }
      h.addStyleName("x-column-header-sel");
      h.activate();

      FocusFrame.get().frame(h);
      container.getAriaSupport().setState("aria-activedescendant", h.getId());
    }
  }

  private final void removeChildren(Element parent) {
    Element child = null;
    while ((child = parent.getFirstChildElement().cast()) != null) {
      parent.removeChild(child);
    }
    String tag = parent.getTagName().toLowerCase();
    if (!tag.equals("table") && !tag.equals("tbody") && !tag.equals("tr") && !tag.equals("td")) {
      parent.setInnerHTML("");
    }
  }
}
