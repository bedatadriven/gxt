/*
 * Sencha GXT 2.3.1 - Sencha for GWT
 * Copyright(c) 2007-2013, Sencha, Inc.
 * licensing@sencha.com
 * 
 * http://www.sencha.com/products/gxt/license/
 */
 package com.extjs.gxt.samples.resources.client.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.PropertyChangeEvent;

public class TeamSales extends BaseModel {

  private static final long serialVersionUID = 2103699184769341265L;

  public TeamSales(String month, int a, int b, int c) {
    setMonth(month);
    setAlphaSales(a);
    setBetaSales(b);
    setGammaSales(c);
    setAvgSales();
  }

  public int getAlphaSales() {
    return get("alphasales") == null ? 0 : (Integer) get("alphasales");
  }

  public int getBetaSales() {
    return get("betasales") == null ? 0 : (Integer) get("betasales");
  }

  public int getGammaSales() {
    return get("gammasales") == null ? 0 : (Integer) get("gammasales");
  }

  public String getMonth() {
    return (String) get("month");
  }

  @Override
  public void notify(ChangeEvent evt) {
    super.notify(evt);

    PropertyChangeEvent e = (PropertyChangeEvent) evt;
    if (!e.getName().equals("avgsales")) {
      setAvgSales();
    }
  }

  public void setAlphaSales(int sales) {
    set("alphasales", sales);
  }

  public void setAvgSales() {
    double avg = (getAlphaSales() + getBetaSales() + getGammaSales()) / 3.0;
    set("avgsales", avg);
  }

  public void setBetaSales(int sales) {
    set("betasales", sales);
  }

  public void setGammaSales(int sales) {
    set("gammasales", sales);
  }

  public void setMonth(String month) {
    set("month", month);
  }
}
