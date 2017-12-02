package com.alexeyosadchy.android.converter.Models;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CURRENCIES".
*/
public class CurrencyDao extends AbstractDao<Currency, Long> {

    public static final String TABLENAME = "CURRENCIES";

    /**
     * Properties of entity Currency.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CurID = new Property(0, Long.class, "curID", true, "_id");
        public final static Property Date = new Property(1, String.class, "date", false, "DATE");
        public final static Property CurAbbreviation = new Property(2, String.class, "curAbbreviation", false, "CUR_ABBREVIATION");
        public final static Property CurScale = new Property(3, Integer.class, "curScale", false, "CUR_SCALE");
        public final static Property CurName = new Property(4, String.class, "curName", false, "CUR_NAME");
        public final static Property CurOfficialRate = new Property(5, Double.class, "curOfficialRate", false, "CUR_OFFICIAL_RATE");
    }

    private DaoSession daoSession;


    public CurrencyDao(DaoConfig config) {
        super(config);
    }
    
    public CurrencyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CURRENCIES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: curID
                "\"DATE\" TEXT NOT NULL ," + // 1: date
                "\"CUR_ABBREVIATION\" TEXT NOT NULL ," + // 2: curAbbreviation
                "\"CUR_SCALE\" INTEGER NOT NULL ," + // 3: curScale
                "\"CUR_NAME\" TEXT NOT NULL ," + // 4: curName
                "\"CUR_OFFICIAL_RATE\" REAL NOT NULL );"); // 5: curOfficialRate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CURRENCIES\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Currency entity) {
        stmt.clearBindings();
 
        Long curID = entity.getCurID();
        if (curID != null) {
            stmt.bindLong(1, curID);
        }
        stmt.bindString(2, entity.getDate());
        stmt.bindString(3, entity.getCurAbbreviation());
        stmt.bindLong(4, entity.getCurScale());
        stmt.bindString(5, entity.getCurName());
        stmt.bindDouble(6, entity.getCurOfficialRate());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Currency entity) {
        stmt.clearBindings();
 
        Long curID = entity.getCurID();
        if (curID != null) {
            stmt.bindLong(1, curID);
        }
        stmt.bindString(2, entity.getDate());
        stmt.bindString(3, entity.getCurAbbreviation());
        stmt.bindLong(4, entity.getCurScale());
        stmt.bindString(5, entity.getCurName());
        stmt.bindDouble(6, entity.getCurOfficialRate());
    }

    @Override
    protected final void attachEntity(Currency entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Currency readEntity(Cursor cursor, int offset) {
        Currency entity = new Currency( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // curID
            cursor.getString(offset + 1), // date
            cursor.getString(offset + 2), // curAbbreviation
            cursor.getInt(offset + 3), // curScale
            cursor.getString(offset + 4), // curName
            cursor.getDouble(offset + 5) // curOfficialRate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Currency entity, int offset) {
        entity.setCurID(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.getString(offset + 1));
        entity.setCurAbbreviation(cursor.getString(offset + 2));
        entity.setCurScale(cursor.getInt(offset + 3));
        entity.setCurName(cursor.getString(offset + 4));
        entity.setCurOfficialRate(cursor.getDouble(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Currency entity, long rowId) {
        entity.setCurID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Currency entity) {
        if(entity != null) {
            return entity.getCurID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Currency entity) {
        return entity.getCurID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}