package com.app.core.memberInfo.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "core_member_info", schema = "life-assistant", catalog = "")
public class CoreMemberInfoEntity {
    private String memberId;
    private String memeberName;
    private byte[] photo;
    private String account;
    private String password;
    private boolean isFrozen;

    @Id
    @Column(name = "MEMBER_ID", nullable = false, length = 36)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "MEMEBER_NAME", nullable = false, length = 100)
    public String getMemeberName() {
        return memeberName;
    }

    public void setMemeberName(String memeberName) {
        this.memeberName = memeberName;
    }

    @Basic
    @Column(name = "PHOTO", nullable = true)
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "ACCOUNT", nullable = false, length = 50)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "IS_FROZEN", nullable = false)
    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoreMemberInfoEntity that = (CoreMemberInfoEntity) o;

        if (isFrozen != that.isFrozen) return false;
        if (memberId != null ? !memberId.equals(that.memberId) : that.memberId != null) return false;
        if (memeberName != null ? !memeberName.equals(that.memeberName) : that.memeberName != null) return false;
        if (!Arrays.equals(photo, that.photo)) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        result = 31 * result + (memeberName != null ? memeberName.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isFrozen ? 1 : 0);
        return result;
    }
}
