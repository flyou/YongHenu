package com.flyou.simplehenu.domain;

import java.util.List;

/**
 * ============================================================
 * 项目名称：HenuCenterPro
 * 包名称：com.flyou.flying.domain
 * 文件名：Image
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2015/9/26 17:08
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 */
public class Image {

    /**
     * Images : [{"imageUrl":"http://ftp371070.host523.zhujiwu.cn/henuCenter/default1.jpg","imageDesc":"巍峨的铁塔湖"},{"imageUrl":"http://ftp371070.host523.zhujiwu.cn/henuCenter/default2.jpg","imageDesc":"华丽的琴键楼"},{"imageUrl":"http://ftp371070.host523.zhujiwu.cn/henuCenter/default3.jpg","imageDesc":"博学的老校长"},{"imageUrl":"http://ftp371070.host523.zhujiwu.cn/henuCenter/default4.jpg","imageDesc":"历史的味道"},{"imageUrl":"http://ftp371070.host523.zhujiwu.cn/henuCenter/default5.jpg","imageDesc":"生机盎然的校园"},{"imageUrl":"http://ftp371070.host523.zhujiwu.cn/henuCenter/default6.jpg","imageDesc":"端庄的大礼堂"}]
     */
    private List<ImagesEntity> Images;

    public void setImages(List<ImagesEntity> Images) {
        this.Images = Images;
    }

    public List<ImagesEntity> getImages() {
        return Images;
    }

    public class ImagesEntity {
        /**
         * imageUrl : http://ftp371070.host523.zhujiwu.cn/henuCenter/default1.jpg
         * imageDesc : 巍峨的铁塔湖
         */
        private String imageUrl;
        private String imageDesc;

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setImageDesc(String imageDesc) {
            this.imageDesc = imageDesc;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getImageDesc() {
            return imageDesc;
        }
    }
}
