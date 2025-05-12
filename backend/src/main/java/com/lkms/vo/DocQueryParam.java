package com.lkms.vo;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lkms.utils.object.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocQueryParam {
    private int pageNum = 1;
    private int pageSize = 10;
    private String office;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishStart;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishEnd;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryStart;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryEnd;
    private int status;
    private String category;
    //    private String suffix;
    // 标题、正文
    private String searchRange;
    private String searchType;
    private String input;
    // 按标题、按发布时间、按生效时间
    private String sortType;
    // 正序、倒序
    private String rankType;

    public String retrieveSortType() {
        if (sortType.equals("title")) return "title.keyword";
        else return sortType;
    }

    public String getSearchRangeField() {
        if (this.searchRange.equals("text")) {
            return "fullContent";
        } else if (this.searchRange.equals("title")) {
            return "title";
        } else {
            return "fullContent";
        }
    }

    public SortOrder getSearchRankType() {
        if (StringUtils.isEmpty(this.getRankType()) || this.getRankType().equals("asc")) {
            return SortOrder.Asc;
        } else {
            return SortOrder.Desc;
        }
    }

    public boolean isPublishExist() {
        return !(this.publishStart == null && this.publishEnd == null);
    }

    public boolean isExpiryExist() {
        return !(this.expiryStart == null && this.expiryEnd == null);
    }
}
