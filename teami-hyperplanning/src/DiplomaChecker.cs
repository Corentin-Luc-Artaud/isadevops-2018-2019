using System;
using System.Collections.Generic;

using teami.Data;
using teami.Data.generator;

namespace teami.hyperplanning
{
    public class DiplomaChecker : IDiplomaChecker
    {
        private PromotionGenerator generator = new PromotionGenerator();
        public List<Student> GetGraduated(int year) => generator.getPromotion(year);
    }
}