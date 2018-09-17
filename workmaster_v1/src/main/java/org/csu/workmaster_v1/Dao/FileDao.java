package org.csu.workmaster_v1.Dao;

import org.csu.workmaster_v1.Entity.File;
public interface FileDao {
    public void saveFile(File file);
    public File findFileByFileId(long fileId);
    public void updateFile(File file);
    public void deleteFileByFileId(long fileId);
}
