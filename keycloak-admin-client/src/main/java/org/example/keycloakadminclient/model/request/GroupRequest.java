package org.example.keycloakadminclient.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.keycloakadminclient.model.dto.GroupDto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    private String groupName;
    public GroupDto toGroupDto(){
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName(groupName);
        return groupDto;
    }
}
