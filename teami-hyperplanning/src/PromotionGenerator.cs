using System;
using System.Collections.Generic;
using System.IO;

namespace teami.Data.generator
{

    public class PromotionGenerator
    {
        private const int population = 400;
        private const string V = "@etu.unice.fr";
        private List<string> firstNames;
        private List<string> LastNames;
        private List<string> Specialities;

        private Dictionary<int, List<Student>> promotions;

        public PromotionGenerator()
        {
            promotions = new Dictionary<int, List<Student>>();
            this.firstNames = new List<string>(File.ReadLines("./ressources/firstNames.txt"));
            this.LastNames = new List<string>(File.ReadLines("./ressources/lastNames.txt"));
            this.Specialities = new List<string>(File.ReadLines("./ressources/speciality.txt"));

        }

        public List<Student> getPromotion(int GraduationYear)
        {
            List<Student> res = null;
            if (promotions.ContainsKey(GraduationYear))
            {
                Console.WriteLine("fetching promotion " + GraduationYear);
                res = new List<Student>();
                promotions.TryGetValue(GraduationYear, out res);
            }
            else
            {
                Console.WriteLine("generating promotion " + GraduationYear);
                res = generatePromotion(GraduationYear);
                promotions.Add(GraduationYear, res);
            }
            return res;
        }

        private List<Student> generatePromotion(int GraduationYear)
        {
            Random random = new Random();
            List<Student> res = new List<Student>();
            for (int i = 0; i < population; ++i)
            {
                var name = firstNames[random.Next(firstNames.Count)];
                var lastName = LastNames[random.Next(LastNames.Count)];
                res.Add(new Student
                {
                    Name = name,
                    LastName = lastName,
                    GraduationYear = GraduationYear,
                    Speciality = Specialities[random.Next(Specialities.Count)],
                    Email = name + "." + lastName + V
                });
            }
            return res;
        }
    }

}