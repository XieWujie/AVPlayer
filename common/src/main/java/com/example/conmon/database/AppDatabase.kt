import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.annotation.Nullable


class AppDb(
    @Nullable context: Context, @Nullable name: String, @Nullable factory: SQLiteDatabase.CursorFactory,
    version: Int
)//创建数据库调用方法
    : SQLiteOpenHelper(context, name, factory, version) {
    /**
     * 第一次创建数据库时调用 在这方法里面可以进行建表
     */
    override fun onCreate(db: SQLiteDatabase) {
        Log.i(TAG, "onCreate: ")
        db.execSQL(sql)
    }

    /**
     * 版本更新的时候调用
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.i(TAG, "onUpgrade: ")
        when (oldVersion) {
            1 -> db.execSQL(sql1)
        }
    }

    companion object {
        private val TAG = "MySQLiteHelper"
        //数据库建表语句
        val sql =
            "create table SqliteDemo (id integer primary key autoincrement, name text(4),address text(5))"
        val sql1 =
            "create table test1 (id integer primary key autoincrement, name text(4),address text(5))"
    }
}