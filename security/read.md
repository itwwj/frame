SpringSecurity原理：

# 一、SpringSecurity的过滤器介绍

SpringSecurity采用的是责任链的设计模式,它有一条很长的过滤器链。现在对这条过滤器链的15个过滤器进行说明:

-  **WebAsyncManagerIntegrationFilter:** 将Security上下文与Spring Web中用于处理异步请求映射的WebAsyncManager进行集成。

- **SecurityContextPersistenceFilter:** 在每次请求处理之前将该请求相关的安全上下文信息加载到SecurityContextHolder中,然后在该次请求处理完成之后,将SecurityContextHolder中关于这次请求的信息存储到一个“仓储"中,然后将SecurityContextHolder中的信息清除,例如在Session中维护一个用户的安全信息就是这个讨滤器处理的。

- **HeaderWriterFilter:** 用于将头信息加入响应中。

- **CsrfFilter:** 用于处理跨站请求伪造。

- **LogoutFilter:** 用于处理退出登录。

- **UsernamePasswordAuthenticationFilter:** 用于处理基于表单的登录请求,从表单中获取用户名和密码。默认情况下处理来自 /login 的请求。从表单中获取用户名和密码时,默认使用的表单name值为 username 和 password,这两个值可以通过设置这个过滤器的usernameParameter和passwordParameter两个参数的值进行修改。

- **DefaultLoginPageGeneratingFilter:** 如果没有配置登录页面,那系统初始化时就会配置这个过滤器,并且用于在需要进行登录时生成一个登录表单页面。

- **DefaultLoginPageGeneratingFilter:** 如果没有配置登录页面,那系统初始化时就会配置这个过滤器,并且用于在需要进行登录时生成一个登录表单页面。

- **RequestCacheAwareFilter:** 用来处理请求的缓存。

- **SecurityContextHolderAwareRequestFilter:** 主要是包装请求对象request。

- **AnonymousAuthenticationFilter:** 检测SecurityContextHolder中是否存在,Authentication对象,如果不存在为其提供一个匿名Authentication.

- **SessionManagementFilter:** 管理session的过滤器

- **ExceptionTranslationFilter:** 处理AccessDeniedException和. AuthenticationException异常。

- **FilterSecurityInterceptor:** 可以看做过滤器链的出口。

- **RememberMeAuthenticationFilter:** 当用户没有登录而直接访问资源时,从cookie里找出用户的信息,如果Spring Security能够识别出用户提供的remember me cookie,用户将不必填写用户名和密码,而是直接登录进入系统,该过滤器默认不开启。


# 二、SpringSecurity基本流程

Spring Security采取过滤链实现认证与授权,只有当前过滤器通过,才能进入下一个过滤器：

![流程图.png](..\a_docs\image\security基本流程.jpg)

绿色部分是认证过滤器,需要我们自己配置,可以配置多个认证过滤器。认证过滤器可以使用Spring Security提供的认证过滤器,也可以自定义过滤器(例如:短信验证)。认正过滤器要在configure (HttpSecurity http)方法中配置,没有配置不生效。下面会重点介绍以下三个过滤器:

- **JsernamePasswordAuthenticationFilter过滤器:** 该过滤器会拦截前端提交的POST方式的登录表单请求,并进行身份认证。

- **ExceptionTranslationFilter过滤器:** 该过滤器不需要我们配置,对于前端提交的请求会直接放行,捕获后续抛出的异常并进行处理(例如:权限访问限制)。

- **FilterSecurityInterceptor过滤器:** 该过滤器是过滤器链的最后一个过滤器,根据资源权限配置来判断当前请求是否有权限访问对应的资源。如果访问受限会抛出相关异常,并由ExceptionTranslationFilter过滤器进行捕获和处理。

# 三、security认证流程

![认证流程.png](..\a_docs\image\security认证流程.jpg)

