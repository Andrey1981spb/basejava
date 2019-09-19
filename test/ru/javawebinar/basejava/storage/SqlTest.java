package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.Config;

import java.io.File;
import java.util.List;

public class SqlTest {
  //  private static final String UUID_1 = UUID.randomUUID().toString();
  //  private static final String UUID_2 = UUID.randomUUID().toString();
  //  private static final String UUID_3 = UUID.randomUUID().toString();
  //  private static final String UUID_4 = UUID.randomUUID().toString();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static Storage sqlStorage = Config.get().getStorage();

    static {
        RESUME_1 = ResumeTestData.getResume(UUID_1, "fullName1");
        RESUME_2 = ResumeTestData.getResume(UUID_2, "fullName2");
        RESUME_3 = ResumeTestData.getResume(UUID_3, "fullName3");
        RESUME_4 = ResumeTestData.getResume(UUID_4, "fullName4");
    }

    public static void main(String[] args) {
        File PROPS = new File( "config/resumes.properties");
        System.out.println(PROPS.getAbsolutePath());

        sqlStorage.save(RESUME_1);
        sqlStorage.save(RESUME_2);
        sqlStorage.save(RESUME_3);
        sqlStorage.save(RESUME_4);
     //    Resume resume = sqlStorage.get(UUID_1);
     //   System.out.println(resume.getUuid());
      //  System.out.println(resume.toString());
      //  System.out.println(resume.getContactInfoMap().toString());
      //  System.out.println(resume.getResumeSections().toString());
        List<Resume> list= sqlStorage.getAllSorted();
        for (Resume resume : list){
            System.out.println(resume.getUuid());
            System.out.println(resume.toString());
            System.out.println(resume.getContactInfoMap().toString());
            System.out.println(resume.getResumeSections().toString());
        }
    }

}
