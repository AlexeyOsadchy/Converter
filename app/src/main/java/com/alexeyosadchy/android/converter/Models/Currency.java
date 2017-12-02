package com.alexeyosadchy.android.converter.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "CURRENCIES")
public class Currency {

    @Id
    @SerializedName("Cur_ID")
    @Expose
    private Long curID;
    @NotNull
    @SerializedName("Date")
    @Expose
    private String date;
    @NotNull
    @SerializedName("Cur_Abbreviation")
    @Expose
    private String curAbbreviation;
    @NotNull
    @SerializedName("Cur_Scale")
    @Expose
    private Integer curScale;
    @NotNull
    @SerializedName("Cur_Name")
    @Expose
    private String curName;
    @NotNull
    @SerializedName("Cur_OfficialRate")
    @Expose
    private Double curOfficialRate;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1033120508)
    private transient CurrencyDao myDao;

    @Generated(hash = 1551148675)
    public Currency(Long curID, @NotNull String date,
                    @NotNull String curAbbreviation, @NotNull Integer curScale,
                    @NotNull String curName, @NotNull Double curOfficialRate) {
        this.curID = curID;
        this.date = date;
        this.curAbbreviation = curAbbreviation;
        this.curScale = curScale;
        this.curName = curName;
        this.curOfficialRate = curOfficialRate;
    }

    @Generated(hash = 1387171739)
    public Currency() {
    }

    @Override
    public String toString() {
        return "Cur_ID: " + curID +
                " Cur_Scale: " + curScale +
                " Cur_OfficialRate: " + curOfficialRate +
                " Cur_Abbreviation: " + curAbbreviation +
                " Cur_Name: " + curName +
                " Date: " + date;
    }

    public Long getCurID() {
        return this.curID;
    }

    public void setCurID(Long curID) {
        this.curID = curID;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurAbbreviation() {
        return this.curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public Integer getCurScale() {
        return this.curScale;
    }

    public void setCurScale(Integer curScale) {
        this.curScale = curScale;
    }

    public String getCurName() {
        return this.curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public Double getCurOfficialRate() {
        return this.curOfficialRate;
    }

    public void setCurOfficialRate(Double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 869658167)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCurrencyDao() : null;
    }
}
