using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;

using teami.Data;

namespace teami.hyperplanning
{

    [ServiceContract]
    public interface IDiplomaChecker
    {
        [OperationContract]
        [WebInvoke(Method = "GET",
            UriTemplate = "getgraduated/{year}",
            ResponseFormat = WebMessageFormat.Json)]
        List<Student> GetGraduated(int year);
    }
}