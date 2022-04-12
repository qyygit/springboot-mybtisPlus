package com.qyy.copy;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.qyy.copy.dto.StudentDto;
import com.qyy.copy.entity.Student;
import net.sf.cglib.beans.BeanCopier;


/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/4/12 10:07
 **/
public class TestMain {
    //12种转换案例
    //1. get\set
    //2. json2Json
    //3. Apache copyProperties
    //4. Spring copyProperties
    //5. Bean Mapping
    //6. Bean Mapping ASM
    //7. BeanCopier
    //8. Orika
    //9. Dozer
    //10. ModelMapper
    //11. JMapper
    //12.MapStruct
    //    目前我整理出，用于对象属性转换有12种，包括：普通的getset、
    //    json2Json、Apache属性拷贝、Spring属性拷贝、bean-mapping、bean-mapping-asm、
    //    BeanCopier、Orika、Dozer、ModelMapper、JMapper、MapStruct

//import org.apache.commons.beanutils.BeanUtils;

    /**
     * @Author: QYY
     * @Description: 点评：其实这种方式也是日常使用的最多的，性能肯定是杠杠的，就是操作起来有点麻烦。
     * 尤其是一大堆属性的 VO 对象转换为 DTO 对象时候。但其实也有一些快捷的操作方式，
     * 比如你可以通过 Shift+Alt 选中所有属性，Shift+Tab 归并到一列，接下来在使用 Alt 选中这一列 ，
     * 批量操作粘贴 userDTO.set 以及快捷键大写属性首字母，最后切换到结尾补充括号和分号，最终格式化一下就搞定了
     * @DateTime: 2022/4/12 10:14
     * @Params: [studentDto]
     * @Return com.qyy.copy.entity.Student
     */
    //1. get\set      性能高,需要手写
    public Student getSet(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setAge(studentDto.getAge());
        student.setHeight(studentDto.getHeight());
        return student;
    }


    //2. JSON2json
    public Student getJson(StudentDto studentDto) {
        String s = JSON.toJSONString(studentDto);
        Student student = JSON.parseObject(s, Student.class);
        return student;
    }


    //3.  Apache copyProperties
//    public Student getApacheCopyProperties(StudentDto studentDto) {
//        Student student = new Student();
//        try {
//            BeanUtils.copyProperties(studentDto,student);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return student;
//    }

    // 4.  Spring copyProperties
//    public Student getSpringCopyProperties(StudentDto studentDto) {
//        Student student = new Student();
//        try {
//            BeanUtils.copyProperties(studentDto, Student.class);
//        } catch (BeansException e) {
//            e.printStackTrace();
//        }
//        return student;
//    }


    // 5.   Bean Mapping
//    public Student getBeanMapping(StudentDto studentDto) {
//        Student student = new Student();
//
//        BeanUtil.copyProperties(studentDto, student;
//
//        return student;
//    }


    // 6.   Bean Mapping ASM
    public Student getBeanMappingASM(StudentDto studentDto) {
        Student student = new Student();
        BeanUtil.copyProperties(studentDto, student);
        return student;
    }


    // 7.   BeanCopier
    public Student getBeanCopier(StudentDto studentDto) {
        Student student = new Student();
        BeanCopier beanCopier = BeanCopier.create(studentDto.getClass(), student.getClass(), false);
        beanCopier.copy(studentDto, studentDto, null);
        return student;
    }

    // 8 .   Orika\
//    @Component
//    public class OrikaAssembler implements IAssembler<UserVO, UserDTO> {
//
//        /**
//         * 构造一个MapperFactory
//         */
//        private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
//
//        static {
//            mapperFactory.classMap(UserDTO.class, UserVO.class)
//                    .field("userId", "userId")  // 字段不一致时可以指定
//                    .byDefault()
//                    .register();
//        }
//
//        @Override
//        public UserDTO sourceToTarget(UserVO var) {
//            return mapperFactory.getMapperFacade().map(var, UserDTO.class);
//        }
//    }


//    private static DozerBeanMapper mapper = new DozerBeanMapper();


    //   9.Dozer
//    public Student getDozer(StudentDto studentDto) {
//        Student student = mapper.map(studentDto, Student.class);
//        System.out.println(student);
//        return student;
//    }


    //   10. ModelMapper
//    public Student getModelMapper(StudentDto studentDto) {
//        private static ModelMapper modelMapper = new ModelMapper();
//
//        static {
//            modelMapper.addMappings(new PropertyMap<UserVO, UserDTO>() {
//                @Override
//                protected void configure() {
//                    // 属性值不一样可以自己操作
//                    map().setUserId(source.getUserId());
//                }
//            });
//        }
//
//        @Override
//        public UserDTO sourceToTarget (UserVO var){
//            return modelMapper.map(var, UserDTO.class);
//        }
//    }


//  12 MapStruct
//    public Student getJMapper(StudentDto studentDto) {
//        JMapper<UserDTO, UserVO> jMapper = new JMapper<>(UserDTO.class, UserVO.class, new JMapperAPI()
//                .add(JMapperAPI.mappedClass(UserDTO.class)
//                        .add(JMapperAPI.attribute("userId")
//                                .value("userId"))
//                        .add(JMapperAPI.attribute("userNickName")
//                                .value("userNickName"))
//                        .add(JMapperAPI.attribute("createTime")
//                                .value("createTime"))
//                ));
//    }


    //   11. JMapper

//    @Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
//    public interface UserDTOMapping extends IMapping<UserVO, UserDTO> {
//
//        /**
//         * 用于测试的单例
//         */
//        IMapping<UserVO, UserDTO> INSTANCE = Mappers.getMapper(UserDTOMapping.class);
//
//        @Mapping(target = "userId", source = "userId")
//        @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
//        @Override
//        UserDTO sourceToTarget(UserVO var1);
//
//        @Mapping(target = "userId", source = "userId")
//        @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
//        @Override
//        UserVO targetToSource(UserDTO var1);
//
//    }

}
