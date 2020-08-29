package com.alpine.team3.http_json_api;

import com.lzx.starrysky.provider.SongInfo;

import java.util.ArrayList;
import java.util.List;

public class MySongList {

    /**
     * privileges : [{"st":0,"flag":68,"subp":1,"fl":128000,"fee":8,"dl":0,"downloadMaxbr":999000,"cp":1,"preSell":false,"cs":false,"toast":false,"playMaxbr":999000,"maxbr":999000,"id":1468998724,"pl":128000,"sp":7,"payed":0}]
     * code : 200
     * playlist : {"privacy":0,"trackNumberUpdateTime":1598540350832,"subscribed":false,"shareCount":0,"adType":0,"trackCount":1,"coverImgId_str":"109951165211313685","specialType":0,"trackIds":[{"at":1598540350832,"v":3,"id":1468998724}],"id":5206108154,"ordered":false,"creator":{"birthday":-2209017600000,"detailDescription":"","backgroundUrl":"http://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg","gender":0,"city":210100,"signature":"","description":"","accountStatus":0,"avatarImgId":109951163250233890,"defaultAvatar":true,"avatarImgIdStr":"109951163250233892","backgroundImgIdStr":"109951162868128395","province":210000,"nickname":"强子拉拉","djStatus":0,"avatarUrl":"http://p1.music.126.net/ma8NC_MpYqC-dK_L81FWXQ==/109951163250233892.jpg","authStatus":0,"vipType":0,"followed":false,"userId":3868336055,"mutual":false,"avatarImgId_str":"109951163250233892","authority":0,"userType":0,"backgroundImgId":109951162868128400},"subscribers":[],"opRecommend":false,"highQuality":false,"commentThreadId":"A_PL_0_5206108154","updateTime":1598540350832,"trackUpdateTime":1598540350865,"userId":3868336055,"tracks":[{"no":0,"rt":"","copyright":0,"fee":8,"mst":9,"pst":0,"pop":100,"dt":291666,"rtype":0,"s_id":0,"rtUrls":[],"id":1468998724,"st":0,"cd":"01","publishTime":0,"cf":"","originCoverType":0,"h":{"br":320000,"fid":0,"size":11668845,"vd":-44291},"mv":0,"al":{"picUrl":"http://p4.music.126.net/WrpQtU8HX0QbdNmXk-O0yw==/109951165211313685.jpg","name":"清风徐来 (2020重唱版)","tns":[],"pic_str":"109951165211313685","id":93608465,"pic":109951165211313680},"l":{"br":128000,"fid":0,"size":4667565,"vd":-40023},"m":{"br":192000,"fid":0,"size":7001325,"vd":-41712},"cp":1418001,"alia":[],"djId":0,"ar":[{"name":"张信哲","tns":[],"alias":[],"id":6454}],"ftype":0,"t":0,"v":3,"name":"清风徐来 (2020重唱版)","mark":8192}],"tags":[],"commentCount":0,"titleImage":0,"cloudTrackCount":0,"coverImgUrl":"http://p1.music.126.net/WrpQtU8HX0QbdNmXk-O0yw==/109951165211313685.jpg","playCount":0,"coverImgId":109951165211313680,"createTime":1598537966024,"name":"清新淡雅","backgroundCoverId":0,"subscribedCount":0,"newImported":false,"status":0}
     */

    private int code;
    private PlaylistBean playlist;
    private List<PrivilegesBean> privileges;

    public List<SongInfo> getSongInfoList(){
        List<SongInfo> list = new ArrayList<>();
        for (PlaylistBean.TracksBean bean:playlist.getTracks()
        ) {
            SongInfo info = new SongInfo();
            info.setSongId(String.valueOf(bean.getId()));
            info.setSongName(bean.getName());
            info.setDuration(bean.getDt());
            info.setArtist(bean.getAr().get(0).getName());
            info.setSongCover(bean.getAl().getPicUrl());
        }
        return list;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PlaylistBean getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistBean playlist) {
        this.playlist = playlist;
    }

    public List<PrivilegesBean> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<PrivilegesBean> privileges) {
        this.privileges = privileges;
    }

    public static class PlaylistBean {
        /**
         * privacy : 0
         * trackNumberUpdateTime : 1598540350832
         * subscribed : false
         * shareCount : 0
         * adType : 0
         * trackCount : 1
         * coverImgId_str : 109951165211313685
         * specialType : 0
         * trackIds : [{"at":1598540350832,"v":3,"id":1468998724}]
         * id : 5206108154
         * ordered : false
         * creator : {"birthday":-2209017600000,"detailDescription":"","backgroundUrl":"http://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg","gender":0,"city":210100,"signature":"","description":"","accountStatus":0,"avatarImgId":109951163250233890,"defaultAvatar":true,"avatarImgIdStr":"109951163250233892","backgroundImgIdStr":"109951162868128395","province":210000,"nickname":"强子拉拉","djStatus":0,"avatarUrl":"http://p1.music.126.net/ma8NC_MpYqC-dK_L81FWXQ==/109951163250233892.jpg","authStatus":0,"vipType":0,"followed":false,"userId":3868336055,"mutual":false,"avatarImgId_str":"109951163250233892","authority":0,"userType":0,"backgroundImgId":109951162868128400}
         * subscribers : []
         * opRecommend : false
         * highQuality : false
         * commentThreadId : A_PL_0_5206108154
         * updateTime : 1598540350832
         * trackUpdateTime : 1598540350865
         * userId : 3868336055
         * tracks : [{"no":0,"rt":"","copyright":0,"fee":8,"mst":9,"pst":0,"pop":100,"dt":291666,"rtype":0,"s_id":0,"rtUrls":[],"id":1468998724,"st":0,"cd":"01","publishTime":0,"cf":"","originCoverType":0,"h":{"br":320000,"fid":0,"size":11668845,"vd":-44291},"mv":0,"al":{"picUrl":"http://p4.music.126.net/WrpQtU8HX0QbdNmXk-O0yw==/109951165211313685.jpg","name":"清风徐来 (2020重唱版)","tns":[],"pic_str":"109951165211313685","id":93608465,"pic":109951165211313680},"l":{"br":128000,"fid":0,"size":4667565,"vd":-40023},"m":{"br":192000,"fid":0,"size":7001325,"vd":-41712},"cp":1418001,"alia":[],"djId":0,"ar":[{"name":"张信哲","tns":[],"alias":[],"id":6454}],"ftype":0,"t":0,"v":3,"name":"清风徐来 (2020重唱版)","mark":8192}]
         * tags : []
         * commentCount : 0
         * titleImage : 0
         * cloudTrackCount : 0
         * coverImgUrl : http://p1.music.126.net/WrpQtU8HX0QbdNmXk-O0yw==/109951165211313685.jpg
         * playCount : 0
         * coverImgId : 109951165211313680
         * createTime : 1598537966024
         * name : 清新淡雅
         * backgroundCoverId : 0
         * subscribedCount : 0
         * newImported : false
         * status : 0
         */

        private int privacy;
        private long trackNumberUpdateTime;
        private boolean subscribed;
        private int shareCount;
        private int adType;
        private int trackCount;
        private String coverImgId_str;
        private int specialType;
        private long id;
        private boolean ordered;
        private CreatorBean creator;
        private boolean opRecommend;
        private boolean highQuality;
        private String commentThreadId;
        private long updateTime;
        private long trackUpdateTime;
        private long userId;
        private int commentCount;
        private int titleImage;
        private int cloudTrackCount;
        private String coverImgUrl;
        private int playCount;
        private long coverImgId;
        private long createTime;
        private String name;
        private int backgroundCoverId;
        private int subscribedCount;
        private boolean newImported;
        private int status;
        private List<TrackIdsBean> trackIds;
        private List<?> subscribers;
        private List<TracksBean> tracks;
        private List<?> tags;

        public int getPrivacy() {
            return privacy;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }

        public long getTrackNumberUpdateTime() {
            return trackNumberUpdateTime;
        }

        public void setTrackNumberUpdateTime(long trackNumberUpdateTime) {
            this.trackNumberUpdateTime = trackNumberUpdateTime;
        }

        public boolean isSubscribed() {
            return subscribed;
        }

        public void setSubscribed(boolean subscribed) {
            this.subscribed = subscribed;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getAdType() {
            return adType;
        }

        public void setAdType(int adType) {
            this.adType = adType;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public String getCoverImgId_str() {
            return coverImgId_str;
        }

        public void setCoverImgId_str(String coverImgId_str) {
            this.coverImgId_str = coverImgId_str;
        }

        public int getSpecialType() {
            return specialType;
        }

        public void setSpecialType(int specialType) {
            this.specialType = specialType;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isOrdered() {
            return ordered;
        }

        public void setOrdered(boolean ordered) {
            this.ordered = ordered;
        }

        public CreatorBean getCreator() {
            return creator;
        }

        public void setCreator(CreatorBean creator) {
            this.creator = creator;
        }

        public boolean isOpRecommend() {
            return opRecommend;
        }

        public void setOpRecommend(boolean opRecommend) {
            this.opRecommend = opRecommend;
        }

        public boolean isHighQuality() {
            return highQuality;
        }

        public void setHighQuality(boolean highQuality) {
            this.highQuality = highQuality;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getTrackUpdateTime() {
            return trackUpdateTime;
        }

        public void setTrackUpdateTime(long trackUpdateTime) {
            this.trackUpdateTime = trackUpdateTime;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getTitleImage() {
            return titleImage;
        }

        public void setTitleImage(int titleImage) {
            this.titleImage = titleImage;
        }

        public int getCloudTrackCount() {
            return cloudTrackCount;
        }

        public void setCloudTrackCount(int cloudTrackCount) {
            this.cloudTrackCount = cloudTrackCount;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public long getCoverImgId() {
            return coverImgId;
        }

        public void setCoverImgId(long coverImgId) {
            this.coverImgId = coverImgId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBackgroundCoverId() {
            return backgroundCoverId;
        }

        public void setBackgroundCoverId(int backgroundCoverId) {
            this.backgroundCoverId = backgroundCoverId;
        }

        public int getSubscribedCount() {
            return subscribedCount;
        }

        public void setSubscribedCount(int subscribedCount) {
            this.subscribedCount = subscribedCount;
        }

        public boolean isNewImported() {
            return newImported;
        }

        public void setNewImported(boolean newImported) {
            this.newImported = newImported;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<TrackIdsBean> getTrackIds() {
            return trackIds;
        }

        public void setTrackIds(List<TrackIdsBean> trackIds) {
            this.trackIds = trackIds;
        }

        public List<?> getSubscribers() {
            return subscribers;
        }

        public void setSubscribers(List<?> subscribers) {
            this.subscribers = subscribers;
        }

        public List<TracksBean> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksBean> tracks) {
            this.tracks = tracks;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }

        public static class CreatorBean {
            /**
             * birthday : -2209017600000
             * detailDescription :
             * backgroundUrl : http://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg
             * gender : 0
             * city : 210100
             * signature :
             * description :
             * accountStatus : 0
             * avatarImgId : 109951163250233890
             * defaultAvatar : true
             * avatarImgIdStr : 109951163250233892
             * backgroundImgIdStr : 109951162868128395
             * province : 210000
             * nickname : 强子拉拉
             * djStatus : 0
             * avatarUrl : http://p1.music.126.net/ma8NC_MpYqC-dK_L81FWXQ==/109951163250233892.jpg
             * authStatus : 0
             * vipType : 0
             * followed : false
             * userId : 3868336055
             * mutual : false
             * avatarImgId_str : 109951163250233892
             * authority : 0
             * userType : 0
             * backgroundImgId : 109951162868128400
             */

            private long birthday;
            private String detailDescription;
            private String backgroundUrl;
            private int gender;
            private int city;
            private String signature;
            private String description;
            private int accountStatus;
            private long avatarImgId;
            private boolean defaultAvatar;
            private String avatarImgIdStr;
            private String backgroundImgIdStr;
            private int province;
            private String nickname;
            private int djStatus;
            private String avatarUrl;
            private int authStatus;
            private int vipType;
            private boolean followed;
            private long userId;
            private boolean mutual;
            private String avatarImgId_str;
            private int authority;
            private int userType;
            private long backgroundImgId;

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(long avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public String getAvatarImgIdStr() {
                return avatarImgIdStr;
            }

            public void setAvatarImgIdStr(String avatarImgIdStr) {
                this.avatarImgIdStr = avatarImgIdStr;
            }

            public String getBackgroundImgIdStr() {
                return backgroundImgIdStr;
            }

            public void setBackgroundImgIdStr(String backgroundImgIdStr) {
                this.backgroundImgIdStr = backgroundImgIdStr;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(int djStatus) {
                this.djStatus = djStatus;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public String getAvatarImgId_str() {
                return avatarImgId_str;
            }

            public void setAvatarImgId_str(String avatarImgId_str) {
                this.avatarImgId_str = avatarImgId_str;
            }

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public long getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(long backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }
        }

        public static class TrackIdsBean {
            /**
             * at : 1598540350832
             * v : 3
             * id : 1468998724
             */

            private long at;
            private int v;
            private int id;

            public long getAt() {
                return at;
            }

            public void setAt(long at) {
                this.at = at;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class TracksBean {
            /**
             * no : 0
             * rt :
             * copyright : 0
             * fee : 8
             * mst : 9
             * pst : 0
             * pop : 100
             * dt : 291666
             * rtype : 0
             * s_id : 0
             * rtUrls : []
             * id : 1468998724
             * st : 0
             * cd : 01
             * publishTime : 0
             * cf :
             * originCoverType : 0
             * h : {"br":320000,"fid":0,"size":11668845,"vd":-44291}
             * mv : 0
             * al : {"picUrl":"http://p4.music.126.net/WrpQtU8HX0QbdNmXk-O0yw==/109951165211313685.jpg","name":"清风徐来 (2020重唱版)","tns":[],"pic_str":"109951165211313685","id":93608465,"pic":109951165211313680}
             * l : {"br":128000,"fid":0,"size":4667565,"vd":-40023}
             * m : {"br":192000,"fid":0,"size":7001325,"vd":-41712}
             * cp : 1418001
             * alia : []
             * djId : 0
             * ar : [{"name":"张信哲","tns":[],"alias":[],"id":6454}]
             * ftype : 0
             * t : 0
             * v : 3
             * name : 清风徐来 (2020重唱版)
             * mark : 8192
             */

            private int no;
            private String rt;
            private int copyright;
            private int fee;
            private int mst;
            private int pst;
            private int pop;
            private int dt;
            private int rtype;
            private int s_id;
            private long id;
            private int st;
            private String cd;
            private int publishTime;
            private String cf;
            private int originCoverType;
            private HBean h;
            private int mv;
            private AlBean al;
            private LBean l;
            private MBean m;
            private int cp;
            private int djId;
            private int ftype;
            private int t;
            private int v;
            private String name;
            private int mark;
            private List<?> rtUrls;
            private List<?> alia;
            private List<ArBean> ar;

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public int getCopyright() {
                return copyright;
            }

            public void setCopyright(int copyright) {
                this.copyright = copyright;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getMst() {
                return mst;
            }

            public void setMst(int mst) {
                this.mst = mst;
            }

            public int getPst() {
                return pst;
            }

            public void setPst(int pst) {
                this.pst = pst;
            }

            public int getPop() {
                return pop;
            }

            public void setPop(int pop) {
                this.pop = pop;
            }

            public int getDt() {
                return dt;
            }

            public void setDt(int dt) {
                this.dt = dt;
            }

            public int getRtype() {
                return rtype;
            }

            public void setRtype(int rtype) {
                this.rtype = rtype;
            }

            public int getS_id() {
                return s_id;
            }

            public void setS_id(int s_id) {
                this.s_id = s_id;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public int getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(int publishTime) {
                this.publishTime = publishTime;
            }

            public String getCf() {
                return cf;
            }

            public void setCf(String cf) {
                this.cf = cf;
            }

            public int getOriginCoverType() {
                return originCoverType;
            }

            public void setOriginCoverType(int originCoverType) {
                this.originCoverType = originCoverType;
            }

            public HBean getH() {
                return h;
            }

            public void setH(HBean h) {
                this.h = h;
            }

            public int getMv() {
                return mv;
            }

            public void setMv(int mv) {
                this.mv = mv;
            }

            public AlBean getAl() {
                return al;
            }

            public void setAl(AlBean al) {
                this.al = al;
            }

            public LBean getL() {
                return l;
            }

            public void setL(LBean l) {
                this.l = l;
            }

            public MBean getM() {
                return m;
            }

            public void setM(MBean m) {
                this.m = m;
            }

            public int getCp() {
                return cp;
            }

            public void setCp(int cp) {
                this.cp = cp;
            }

            public int getDjId() {
                return djId;
            }

            public void setDjId(int djId) {
                this.djId = djId;
            }

            public int getFtype() {
                return ftype;
            }

            public void setFtype(int ftype) {
                this.ftype = ftype;
            }

            public int getT() {
                return t;
            }

            public void setT(int t) {
                this.t = t;
            }

            public int getV() {
                return v;
            }

            public void setV(int v) {
                this.v = v;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getMark() {
                return mark;
            }

            public void setMark(int mark) {
                this.mark = mark;
            }

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
            }

            public List<?> getAlia() {
                return alia;
            }

            public void setAlia(List<?> alia) {
                this.alia = alia;
            }

            public List<ArBean> getAr() {
                return ar;
            }

            public void setAr(List<ArBean> ar) {
                this.ar = ar;
            }

            public static class HBean {
                /**
                 * br : 320000
                 * fid : 0
                 * size : 11668845
                 * vd : -44291
                 */

                private int br;
                private int fid;
                private int size;
                private int vd;

                public int getBr() {
                    return br;
                }

                public void setBr(int br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getVd() {
                    return vd;
                }

                public void setVd(int vd) {
                    this.vd = vd;
                }
            }

            public static class AlBean {
                /**
                 * picUrl : http://p4.music.126.net/WrpQtU8HX0QbdNmXk-O0yw==/109951165211313685.jpg
                 * name : 清风徐来 (2020重唱版)
                 * tns : []
                 * pic_str : 109951165211313685
                 * id : 93608465
                 * pic : 109951165211313680
                 */

                private String picUrl;
                private String name;
                private String pic_str;
                private int id;
                private long pic;
                private List<?> tns;

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPic_str() {
                    return pic_str;
                }

                public void setPic_str(String pic_str) {
                    this.pic_str = pic_str;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public long getPic() {
                    return pic;
                }

                public void setPic(long pic) {
                    this.pic = pic;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }
            }

            public static class LBean {
                /**
                 * br : 128000
                 * fid : 0
                 * size : 4667565
                 * vd : -40023
                 */

                private int br;
                private int fid;
                private int size;
                private int vd;

                public int getBr() {
                    return br;
                }

                public void setBr(int br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getVd() {
                    return vd;
                }

                public void setVd(int vd) {
                    this.vd = vd;
                }
            }

            public static class MBean {
                /**
                 * br : 192000
                 * fid : 0
                 * size : 7001325
                 * vd : -41712
                 */

                private int br;
                private int fid;
                private int size;
                private int vd;

                public int getBr() {
                    return br;
                }

                public void setBr(int br) {
                    this.br = br;
                }

                public int getFid() {
                    return fid;
                }

                public void setFid(int fid) {
                    this.fid = fid;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public int getVd() {
                    return vd;
                }

                public void setVd(int vd) {
                    this.vd = vd;
                }
            }

            public static class ArBean {
                /**
                 * name : 张信哲
                 * tns : []
                 * alias : []
                 * id : 6454
                 */

                private String name;
                private int id;
                private List<?> tns;
                private List<?> alias;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public List<?> getTns() {
                    return tns;
                }

                public void setTns(List<?> tns) {
                    this.tns = tns;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }
    }

    public static class PrivilegesBean {
        /**
         * st : 0
         * flag : 68
         * subp : 1
         * fl : 128000
         * fee : 8
         * dl : 0
         * downloadMaxbr : 999000
         * cp : 1
         * preSell : false
         * cs : false
         * toast : false
         * playMaxbr : 999000
         * maxbr : 999000
         * id : 1468998724
         * pl : 128000
         * sp : 7
         * payed : 0
         */

        private int st;
        private int flag;
        private int subp;
        private int fl;
        private int fee;
        private int dl;
        private int downloadMaxbr;
        private int cp;
        private boolean preSell;
        private boolean cs;
        private boolean toast;
        private int playMaxbr;
        private int maxbr;
        private int id;
        private int pl;
        private int sp;
        private int payed;

        public int getSt() {
            return st;
        }

        public void setSt(int st) {
            this.st = st;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public int getSubp() {
            return subp;
        }

        public void setSubp(int subp) {
            this.subp = subp;
        }

        public int getFl() {
            return fl;
        }

        public void setFl(int fl) {
            this.fl = fl;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getDl() {
            return dl;
        }

        public void setDl(int dl) {
            this.dl = dl;
        }

        public int getDownloadMaxbr() {
            return downloadMaxbr;
        }

        public void setDownloadMaxbr(int downloadMaxbr) {
            this.downloadMaxbr = downloadMaxbr;
        }

        public int getCp() {
            return cp;
        }

        public void setCp(int cp) {
            this.cp = cp;
        }

        public boolean isPreSell() {
            return preSell;
        }

        public void setPreSell(boolean preSell) {
            this.preSell = preSell;
        }

        public boolean isCs() {
            return cs;
        }

        public void setCs(boolean cs) {
            this.cs = cs;
        }

        public boolean isToast() {
            return toast;
        }

        public void setToast(boolean toast) {
            this.toast = toast;
        }

        public int getPlayMaxbr() {
            return playMaxbr;
        }

        public void setPlayMaxbr(int playMaxbr) {
            this.playMaxbr = playMaxbr;
        }

        public int getMaxbr() {
            return maxbr;
        }

        public void setMaxbr(int maxbr) {
            this.maxbr = maxbr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPl() {
            return pl;
        }

        public void setPl(int pl) {
            this.pl = pl;
        }

        public int getSp() {
            return sp;
        }

        public void setSp(int sp) {
            this.sp = sp;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }
    }
}
