package cn.bd.dailyReport.dao;

import cn.bd.dailyReport.model.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by 赵传志 on 2018/4/25.
 */
public interface FileDao {

    /**
     * 将上传的文件的一些基本信息存入数据库中
     * @param file
     */
    @Insert("insert into `file` (id, title, originalName, uuIdName, `desc`, uploader, uploadTime) values(null, #{title}, #{originalName}, #{uuIdName}, #{desc}, #{uploader}, #{uploadTime})")
    void create(UploadFile file);

    @Select("select * from `file` where uploadTime like concat('%',#{uploadTime},'%') and uploader like concat('%',#{uploader},'%') order by uploadTime desc limit #{page},#{pageSize}  ")
    List<UploadFile> findAll(@Param("page") int page, @Param("pageSize") int pageSize, @Param("uploadTime") String uploadTime, @Param("uploader") String uploader);

    // 获取文件表中所有记录条数
    @Select("select * from file where uploadTime like concat('%',#{uploadTime},'%') and uploader like concat('%',#{uploader},'%')")
    List<UploadFile> getAll(@Param("uploadTime") String uploadTime, @Param("uploader") String uploader);
}
