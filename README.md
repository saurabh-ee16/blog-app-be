# blog-app-be

Java Spring boot back-end app
=============================

 
 - location: Blog-web-app/blog-app-BE/blog-app
 - Run command: **mvn spring-boot:run**     backend application will start at port localhost:8081 
 - In memory database H2 database can be accessed by http://localhost:8081/h2-console/
 - H2 console --> Driver Class = org.h2.Driver , JdbcUrl = jdbc:h2:mem:testdb , Username = sa , password = "" (empty)
 - User authentication is done with Spring security using email and password entered by user

APIs created at backend
======================
User authentication

- /user/sigin      GET
- /user/signup    POST
- user/role-auth    POST

Blog post
 
 - /blog/get-blogs     GET --> get all blogs
 - /blog/id      PUT    --> approve a blog
 - /blog/update     PUT --> update a blog
 - /blog/id     GET --> get blog with id 
 - /blog/create     POST --> create a new blog
 - /blog/id     DELETE -->delete or reject a blog
