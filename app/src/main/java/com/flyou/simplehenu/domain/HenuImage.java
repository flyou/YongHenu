package com.flyou.simplehenu.domain;

import java.util.List;

/**
 * ============================================================
 * 项目名称：
 * 包名称：
 * 文件名：HenuImage
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/3/9 16:30
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 */
public class HenuImage {

    private List<String> image;
    private List<String> imageThumbnail;

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void setImageThumbnail(List<String> imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public List<String> getImage() {
        return image;
    }

    public List<String> getImageThumbnail() {
        return imageThumbnail;
    }
}
