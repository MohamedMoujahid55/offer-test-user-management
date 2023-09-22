package fr.air.france.technicaltestairfrance.mappers;

import fr.air.france.technicaltestairfrance.dtos.UserDto;
import fr.air.france.technicaltestairfrance.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is a sample Spring class.
 *
 * <p>This class permit to map UserEntity to UserDto and vice versa .
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@Service
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    /**
     * This method map a userDto to userEntity.
     *
     * @param UserDTo.
     * @return UserEntity.
     */
    public UserEntity userDtoToUserEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    /**
     * This method map a UserEntity to UserDto.
     *
     * @param UserEntity.
     * @return UserDto.
     */

    public UserDto userEntityToUserDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
}
