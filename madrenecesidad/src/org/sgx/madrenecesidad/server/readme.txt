this is a mess - current state of things : ignore core and local - 



this package contains all serverside logic. 

the services classes are divided vertically in three : core, prod, test.

core: are interfaces and implementations that perform everything related 
to CRUD operations on entities. They are based on artificial User type. This is 
servverside only and do not depend on GWT RPC or appengine.user.

prod: a RPC service implementation that will use appengine.user and provide GWT 
RPC for the production application to use. It will talk to core passing the artificial user. 

test: a RPC service implementation that will accept 

local and web talk with core: 



 of the core service functionality, it performs everythingnot counting the user auth and independent of RPC. Name example: AppleCoreService. Cor operations are responsible of CRUD data persistence, data indexing, data caching.   

local: ("local service classes") provide RPC with the core functionality so they can be tested without user auth. these RPC must not be published in production !!!!. Name example: AppleLocalService. Local can wrap a some core service methods. Responsible of providing the RPC interface to core classes.   

web : ("web service classes") use the local service classes and add logic about the user and user permission.  
they wrap/extend each operation from local that we want to publish and add user logic. Name example: AppleWebService. Responsible of providing user logic - call just like local.
The user datatype   

web operations are based on local and local operations are based on core. local RPC can be tested using rpc-sync.







Note: if user sessions could be simulated on rpc-sync then local does not make sense and can be eliminated.  