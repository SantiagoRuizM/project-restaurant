package com.dish.servicedish.dtos;

public class PageGeneric<T> {

    private int totalPages;
    private int numberPage;
    private int registersPage;
    private T content;

    public PageGeneric() {
    }

    public PageGeneric(int totalPages, int numberPage, int registersPage, T content) {
        this.totalPages = totalPages;
        this.numberPage = numberPage;
        this.registersPage = registersPage;
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getRegistersPage() {
        return registersPage;
    }

    public void setRegistersPage(int registersPage) {
        this.registersPage = registersPage;
    }
}
