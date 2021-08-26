package com.blk.blkpangkep.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Options {

    @SerializedName("last_editor")
    @Expose
    private int lastEditor;
    @SerializedName("table_head")
    @Expose
    private boolean tableHead;
    @SerializedName("table_foot")
    @Expose
    private boolean tableFoot;
    @SerializedName("alternating_row_colors")
    @Expose
    private boolean alternatingRowColors;
    @SerializedName("row_hover")
    @Expose
    private boolean rowHover;
    @SerializedName("print_name")
    @Expose
    private boolean printName;
    @SerializedName("print_name_position")
    @Expose
    private String printNamePosition;
    @SerializedName("print_description")
    @Expose
    private boolean printDescription;
    @SerializedName("print_description_position")
    @Expose
    private String printDescriptionPosition;
    @SerializedName("extra_css_classes")
    @Expose
    private String extraCssClasses;
    @SerializedName("use_datatables")
    @Expose
    private boolean useDatatables;
    @SerializedName("datatables_sort")
    @Expose
    private boolean datatablesSort;
    @SerializedName("datatables_filter")
    @Expose
    private boolean datatablesFilter;
    @SerializedName("datatables_paginate")
    @Expose
    private boolean datatablesPaginate;
    @SerializedName("datatables_lengthchange")
    @Expose
    private boolean datatablesLengthchange;
    @SerializedName("datatables_paginate_entries")
    @Expose
    private int datatablesPaginateEntries;
    @SerializedName("datatables_info")
    @Expose
    private boolean datatablesInfo;
    @SerializedName("datatables_scrollx")
    @Expose
    private boolean datatablesScrollx;
    @SerializedName("datatables_custom_commands")
    @Expose
    private String datatablesCustomCommands;

    public int getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(int lastEditor) {
        this.lastEditor = lastEditor;
    }

    public boolean isTableHead() {
        return tableHead;
    }

    public void setTableHead(boolean tableHead) {
        this.tableHead = tableHead;
    }

    public boolean isTableFoot() {
        return tableFoot;
    }

    public void setTableFoot(boolean tableFoot) {
        this.tableFoot = tableFoot;
    }

    public boolean isAlternatingRowColors() {
        return alternatingRowColors;
    }

    public void setAlternatingRowColors(boolean alternatingRowColors) {
        this.alternatingRowColors = alternatingRowColors;
    }

    public boolean isRowHover() {
        return rowHover;
    }

    public void setRowHover(boolean rowHover) {
        this.rowHover = rowHover;
    }

    public boolean isPrintName() {
        return printName;
    }

    public void setPrintName(boolean printName) {
        this.printName = printName;
    }

    public String getPrintNamePosition() {
        return printNamePosition;
    }

    public void setPrintNamePosition(String printNamePosition) {
        this.printNamePosition = printNamePosition;
    }

    public boolean isPrintDescription() {
        return printDescription;
    }

    public void setPrintDescription(boolean printDescription) {
        this.printDescription = printDescription;
    }

    public String getPrintDescriptionPosition() {
        return printDescriptionPosition;
    }

    public void setPrintDescriptionPosition(String printDescriptionPosition) {
        this.printDescriptionPosition = printDescriptionPosition;
    }

    public String getExtraCssClasses() {
        return extraCssClasses;
    }

    public void setExtraCssClasses(String extraCssClasses) {
        this.extraCssClasses = extraCssClasses;
    }

    public boolean isUseDatatables() {
        return useDatatables;
    }

    public void setUseDatatables(boolean useDatatables) {
        this.useDatatables = useDatatables;
    }

    public boolean isDatatablesSort() {
        return datatablesSort;
    }

    public void setDatatablesSort(boolean datatablesSort) {
        this.datatablesSort = datatablesSort;
    }

    public boolean isDatatablesFilter() {
        return datatablesFilter;
    }

    public void setDatatablesFilter(boolean datatablesFilter) {
        this.datatablesFilter = datatablesFilter;
    }

    public boolean isDatatablesPaginate() {
        return datatablesPaginate;
    }

    public void setDatatablesPaginate(boolean datatablesPaginate) {
        this.datatablesPaginate = datatablesPaginate;
    }

    public boolean isDatatablesLengthchange() {
        return datatablesLengthchange;
    }

    public void setDatatablesLengthchange(boolean datatablesLengthchange) {
        this.datatablesLengthchange = datatablesLengthchange;
    }

    public int getDatatablesPaginateEntries() {
        return datatablesPaginateEntries;
    }

    public void setDatatablesPaginateEntries(int datatablesPaginateEntries) {
        this.datatablesPaginateEntries = datatablesPaginateEntries;
    }

    public boolean isDatatablesInfo() {
        return datatablesInfo;
    }

    public void setDatatablesInfo(boolean datatablesInfo) {
        this.datatablesInfo = datatablesInfo;
    }

    public boolean isDatatablesScrollx() {
        return datatablesScrollx;
    }

    public void setDatatablesScrollx(boolean datatablesScrollx) {
        this.datatablesScrollx = datatablesScrollx;
    }

    public String getDatatablesCustomCommands() {
        return datatablesCustomCommands;
    }

    public void setDatatablesCustomCommands(String datatablesCustomCommands) {
        this.datatablesCustomCommands = datatablesCustomCommands;
    }

}
