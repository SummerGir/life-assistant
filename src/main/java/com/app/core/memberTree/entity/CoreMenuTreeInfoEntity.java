package com.app.core.memberTree.entity;

import javax.persistence.*;

@Entity
@Table(name = "core_menu_tree_info", schema = "life-assistant", catalog = "")
public class CoreMenuTreeInfoEntity {
    private String menuId;
    private Integer menuLevel;
    private String outlineLevel;
    private String title;
    private String urlId;
    private String icon;
    private Boolean type;
    private Boolean isShow;

    @Id
    @Column(name = "MENU_ID", nullable = false, length = 36)
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "MENU_LEVEL", nullable = true)
    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    @Basic
    @Column(name = "OUTLINE_LEVEL", nullable = true, length = 200)
    public String getOutlineLevel() {
        return outlineLevel;
    }

    public void setOutlineLevel(String outlineLevel) {
        this.outlineLevel = outlineLevel;
    }

    @Basic
    @Column(name = "TITLE", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "URL_ID", nullable = true, length = 36)
    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    @Basic
    @Column(name = "ICON", nullable = true, length = 200)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "TYPE", nullable = true)
    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Basic
    @Column(name = "IS_SHOW", nullable = true)
    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoreMenuTreeInfoEntity that = (CoreMenuTreeInfoEntity) o;

        if (menuId != null ? !menuId.equals(that.menuId) : that.menuId != null) return false;
        if (menuLevel != null ? !menuLevel.equals(that.menuLevel) : that.menuLevel != null) return false;
        if (outlineLevel != null ? !outlineLevel.equals(that.outlineLevel) : that.outlineLevel != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (urlId != null ? !urlId.equals(that.urlId) : that.urlId != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (isShow != null ? !isShow.equals(that.isShow) : that.isShow != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuId != null ? menuId.hashCode() : 0;
        result = 31 * result + (menuLevel != null ? menuLevel.hashCode() : 0);
        result = 31 * result + (outlineLevel != null ? outlineLevel.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (urlId != null ? urlId.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
        return result;
    }
}
