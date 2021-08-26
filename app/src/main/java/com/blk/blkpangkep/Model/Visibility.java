package com.blk.blkpangkep.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Visibility {

    @SerializedName("rows")
    @Expose
    private List<Integer> rows = null;
    @SerializedName("columns")
    @Expose
    private List<Integer> columns = null;

    public List<Integer> getRows() {
        return rows;
    }

    public void setRows(List<Integer> rows) {
        this.rows = rows;
    }

    public List<Integer> getColumns() {
        return columns;
    }

    public void setColumns(List<Integer> columns) {
        this.columns = columns;
    }

}
