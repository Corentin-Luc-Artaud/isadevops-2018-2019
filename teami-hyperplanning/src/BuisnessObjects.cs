using System.Runtime.Serialization;
using System;

namespace teami.Data
{
    [DataContract(Namespace = "hyper_planning/student/data", Name = "Student")]
    public class Student
    {
        [DataMember(Name = "firstName")]
        public string Name { set; get; }

        [DataMember(Name = "lastName")]
        public string LastName { set; get; }

        [DataMember(Name = "graduatingYear")]
        public int GraduationYear { set; get; }

        [DataMember(Name = "email")]
        public string Email { set; get; }

        [DataMember(Name = "speciality")]
        public string Speciality { set; get; }

        public override string ToString() => "Student[ " + Name + " : " + "LastName" + " ]";
    }
}
