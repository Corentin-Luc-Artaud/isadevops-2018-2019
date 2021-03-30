using System;
using System.IO;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Threading;


using teami.hyperplanning;

namespace teami.service
{
    public class Service
    {
        Boolean standalone = false;
        private string Locker = "server.LOCK";

        //private string PORT = "9090";
        private string URL = "http://localhost:9090";

        private WebServiceHost Host;


        public Service(string[] args)
        {
            string baseURL = URL;//+ ':' + PORT;
            this.configure(args);
            Host = new WebServiceHost(typeof(DiplomaChecker), new Uri(baseURL));
            Host.AddServiceEndpoint(typeof(IDiplomaChecker), new WebHttpBinding(), "");
        }

        public void start()
        {
            try
            {
                Host.Open();
                Console.WriteLine("Hyper Planning listening on " + URL);
                if (standalone) { lockServer(); } else { interactive(); }

            }
            catch (CommunicationException cex)
            {
                Console.WriteLine("An exception occurred: {0}", cex.Message);
                Host.Abort();
            }
        }

        private void stop()
        {
            Host.Close();
            Console.WriteLine("Server shutdown complete!");
        }

        private void interactive()
        {
            Console.WriteLine("Hit Return to shutdown the server.");
            Console.ReadLine();
            stop();
        }

        private void lockServer()
        {
            File.Create(Locker);
            Console.WriteLine("Delete the lock file (" + Locker + ") to stop the server");
            watchforLockRemoval();
        }

        private void watchforLockRemoval()
        {
            var shouldStop = false;
            while (!shouldStop)
            {
                Thread.Sleep(1000);
                if (!File.Exists(Locker)) { shouldStop = true; }
            }
            stop();
        }

        private void configure(string[] args)
        {
            // int portIndex = Array.FindLastIndex(args, key => key == "--port");
            // if (portIndex != -1) { this.PORT = args[portIndex + 1]; }
            int urlIndex = Array.FindLastIndex(args, key => key == "--url");
            if (urlIndex != -1) { this.URL = args[urlIndex + 1]; }
            int standaloneIndex = Array.FindLastIndex(args, key => key == "--standalone");
            if (standaloneIndex != -1) { this.standalone = true; }
        }


        public static void Main(string[] args)
        {
            Service service = new Service(args);
            service.start();
        }
    }
}