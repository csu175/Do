package org.csu.workmaster_v1.Dao;

import org.csu.workmaster_v1.Entity.Group;

public interface GroupDao {
    public void saveGroup(Group group);
    public Group findGroupByGroupId(long groupid);
    public void updateGroup(Group group);
    public void deleteGroupByGroupId(long groupid);
    public Group findGroupByGroupName(String groupname);
}
