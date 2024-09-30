package com.jhonarias91.data;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page){
        this.page = page;
    }
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
