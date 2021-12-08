package com.dailynews.Newsdaily.controller;
import com.dailynews.Newsdaily.controller.vm.LoginVM;
import com.dailynews.Newsdaily.domen.*;
import com.dailynews.Newsdaily.exception.MathematicalException;
import com.dailynews.Newsdaily.listener.LoginListener;
import com.dailynews.Newsdaily.repository.*;
import com.dailynews.Newsdaily.service.*;

//import com.dailynews.Newsdaily.utilities.AuthResponse;
import com.dailynews.Newsdaily.utilities.JwtUtil;
import com.dailynews.Newsdaily.utilities.Slug;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin("http://localhost:8080")
@RestController
public class NewsController {
   private NewsUserDetailsService newsUserDetailsService;
    private final NewsServices newsServices;
    private final NewsCategoriesAndSubCategoriesRepository newsCategoryServices;
    private final ViewsServices viewsServices;
    private final NewsImageServices newsImageServices;
    private final UserService userService;
    private final UserSecurityService userSecurityService;
    private final JavaMailSender javaMailSender;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserVerificationTokenRepository userVerificationTokenRepository;
    private final RoleService roleService;
    private final NewsVotingRepository newsVotingRepository;
    private final NewsCommentsRepository newsCommentsRepository;
    private final NewsCategoriesAndSubCategoriesRepository newsSubCategoryRepository;

    public NewsController(NewsCategoriesAndSubCategoriesRepository newsCategoryServices, NewsServices newsServices, ViewsServices viewsServices, NewsImageServices newsImageServices, UserService userService, UserSecurityService userSecurityService, UsersService usersService, UserVerificationTokenRepository userVerificationTokenRepository, NewsRepository newsRepository, RoleService roleService, NewsVotingRepository newsVotingRepository, NewsCommentsRepository newsCommentsRepository, NewsCategoriesAndSubCategoriesRepository newsSubCategoryRepository, NewsCategoriesAndSubCategoriesRepository categoriesAndSubCategories, JavaMailSender javaMailSender, AuthenticationManager authenticationManager) {
        this.newsCategoryServices = newsCategoryServices;
        this.newsServices = newsServices;
        this.viewsServices = viewsServices;
        this.newsImageServices = newsImageServices;
        this.userService = userService;
        this.userSecurityService = userSecurityService;
        this.usersService = usersService;
        this.userVerificationTokenRepository = userVerificationTokenRepository;
        this.newsRepository = newsRepository;
        this.roleService = roleService;
        this.newsVotingRepository = newsVotingRepository;
        this.newsCommentsRepository = newsCommentsRepository;
        this.newsSubCategoryRepository = newsSubCategoryRepository;
        this.categoriesAndSubCategories = categoriesAndSubCategories;
        this.javaMailSender = javaMailSender;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        return new ResponseEntity<>(newsServices.findAllNews(), HttpStatus.OK);
    }

    @GetMapping("/news/sasa")
    public ResponseEntity<List<News>> getAllNewsss() {
        return new ResponseEntity<>(newsServices.findAllNews(), HttpStatus.OK);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> showOneNews(@PathVariable long id) {
        return new ResponseEntity<>(newsServices.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/news/{id}")
    public String deleteOneNews(@PathVariable long id) {
        newsServices.deleteById(id);
        return "successfully deleted";
    }

    @PostMapping("/news")
    public ResponseEntity<News> addNews(@RequestBody News news) {
        News newsproduct=newsRepository.save(news);
        return ResponseEntity.ok(newsproduct);
    }

    private static final Logger logger = Logger.getLogger(String.valueOf(NewsController.class));

   @GetMapping("/newscategory")

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("news", newsServices.findAllNews());
        List<News> n = newsServices.findAllNews();

        for (News news : n) {
            System.out.println(news.getImage());
        }
        modelAndView.addObject("mostViewed", viewsServices.getPopularNewsByViews());
        modelAndView.addObject("category", newsCategoryServices.findAllNewsCategory());
        modelAndView.addObject("business", newsCategoryServices.findByCategoryName("business"));
        modelAndView.addObject("politics", newsCategoryServices.findByCategoryName("politics"));
        modelAndView.addObject("entertainment", newsCategoryServices.findByCategoryName("entertainment"));
        modelAndView.addObject("sports", newsCategoryServices.findByCategoryName("sports"));
        modelAndView.addObject("science", newsCategoryServices.findByCategoryName("science"));

        // News obj = new News();
        //logger.info("this is good for checking" + obj);
//        final String appUrl = "http://" + "localhost" + ":" + 8080 + "/news";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("das.partho99@gmail.com");
//        message.setSubject("Testing spring mail");
//        message.setText(appUrl);
//        javaMailSender.send(message);
        return modelAndView;
    }

    @GetMapping("/fragments")
    public ModelAndView showNavBar() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("showNavBar", newsCategoryServices.findAllNewsCategory());
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @GetMapping("/Bangladesh")
    public ModelAndView bangladesh() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("Bangladesh"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("category-bangladesh");
        return mv;
    }

    @GetMapping("/Sports")
    public ModelAndView sports() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("Sports"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("category-sports");
        return mv;
    }

    @GetMapping("/International")
    public ModelAndView international() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("International"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("category-international");
        return mv;
    }

    @GetMapping("/Entertainment")
    public ModelAndView entertainment() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("Entertainment"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("category-entertainment");
        return mv;
    }

    @GetMapping("/Politics")
    public ModelAndView politics() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("Politics"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("category-politics");
        return mv;
    }

    @GetMapping("/Science")
    public ModelAndView science() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("Science"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("category-science");
        return mv;
    }

    @GetMapping("/sports-Cricket")
    public ModelAndView sportsCricket() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsCategoryServices.findNewsSubCategoriesByCategoryName("Sports"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("index-4");
        return mv;
    }

    @GetMapping("/entertainment-Movies")
    public ModelAndView entertainmentMovies() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorye", newsCategoryServices.findAllNewsCategory());
        mv.addObject("hasNewsSubCategory", newsSubCategoryRepository.findNewsSubCategoriesByCategoryName("Movies"));
        mv.addObject("category", newsCategoryServices.findAllNewsCategory());
        mv.setViewName("entertainment-movies");
        return mv;
    }

    @GetMapping("/popularnews")
    public List<Views> popularNews() {
        //System.out.print(viewsServices.getPopularNewsByViews());
        return viewsServices.getPopularNewsByViews();
    }

    @GetMapping("/{categoryName}")
    public List<News> showAllNewsByCategoryName(@PathVariable("categoryName") String categoryName) {
        return newsCategoryServices.findByCategoryName(categoryName);
    }

    @GetMapping("/{categoryName}/news/{newsHeading}/{newsId}")
    public ModelAndView showSpecificNews(@PathVariable("categoryName") String categoryName, @PathVariable("newsHeading") String newsHeading, @PathVariable("newsId") long newsId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("singleNews", newsServices.findById(newsId));
        modelAndView.addObject("newsImage", newsImageServices.findByNews(newsId));
        modelAndView.addObject("newsComment", new NewsComments());
        modelAndView.setViewName("post-details");
        Long userId = null;
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            NewsUserDetails s = (NewsUserDetails) loggedInUser.getPrincipal();
            userId = s.getId();
            //System.out.println( s.getId());
        } catch (Exception e) {
        }
        if (loggedInUser.getPrincipal().equals("anonymousUser")) {
        } else {
            newsVotingRepository.upVoteCounting(userId, newsId);
        }
        return modelAndView;
    }

    //@PreAuthorize("hasAnyRole('ADMIN')")
   //@GetMapping("/newsUpload")
   //public ModelAndView newsUpload() {
   //    ModelAndView modelAndView = new ModelAndView();
   //    modelAndView.addObject("news", new News());
   //    modelAndView.addObject("newsCategory", newsCategoryServices.findAllNewsCategoriesAndSubCategories());
   //    modelAndView.setViewName("newsuploader");
   //    return modelAndView;
   //}


   //@Value("${image.upload.path}")
   //String uploadPath;

   //@Value("${image.upload.uri}")
   //String uploadUri;

    //Mapping("/image/upload")
    //c String upload(@RequestPart MultipartFile upload, @RequestParam(value = "CKEditorFuncNum") String callback, HttpServletRequest request) throws IOException {
    //ystem.out.println(upload.getOriginalFilename());
    //tring sourceName = upload.getOriginalFilename();
    //tring sourceExt = FilenameUtils.getExtension(sourceName);
    //ile destFile;
    //tring destFileName;
    //o {
    //   destFileName = RandomStringUtils.randomAlphabetic(8).concat(".").concat(sourceExt);
    //   destFile = new File(uploadPath.concat(destFileName));
    // while (destFile.exists());
    //estFile.getParentFile().mkdirs();
    //pload.transferTo(destFile);
    //tring imgUrl = request.getScheme().concat("://").concat(request.getServerName()).concat(":").concat(String.valueOf(request.getServerPort())).concat(uploadUri).concat(destFileName);
    //tringBuffer sb = new StringBuffer();
    //b.append("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(");
    //b.append(callback);
    //b.append(",'");
    //b.append(imgUrl);
    //b.append("','image uploaded successfully!!')</script>");
    //eturn sb.toString();
    //

   // @PostMapping("/saveNewsCredentials")
   // public String saveNews(@ModelAttribute News news, @RequestPart(value = "file") MultipartFile[] files, BindingResult bindingResult) throws IOException {
   //     String sourceName;
   //     String sourceExt;
   //     File destFile;
   //     String destFileName;
   //     News newsImage = new News();
   //     NewsImage image = new NewsImage();
   //     long newsId = newsServices.saveNews(news);
   //     if (files[0].getBytes().length > 0) {
   //         for (MultipartFile file : files) {
   //             sourceName = file.getOriginalFilename();
   //             sourceExt = FilenameUtils.getExtension(sourceName);
   //             do {
   //                 destFileName = RandomStringUtils.randomAlphabetic(8).concat(".").concat(sourceExt);
   //                 destFile = new File(uploadPath.concat(destFileName));
   //             }
   //             while (destFile.exists());
   //             file.transferTo(destFile);
   //             image.setActive(true);
   //             image.setImageCreationDTM(new java.util.Date());
   //             image.setImageUpdationDTM(new java.util.Date());
   //             newsImage.setNewsId(newsId);
   //             image.setNews(newsImage);
   //             String s = "/images/" + destFileName;
   //             newsImageServices.saveImage(new NewsImage(s, true, new java.util.Date(), new java.util.Date(), newsImage));
   //         }
   //     }
   //     return "success";
   // }

//    @GetMapping("/showCate")
//    public List<NewsCategory> imageCate() {
//
//        return newsCategoryServices.findAllNewsCategory();
//    }



    @PostMapping("/register")
    public ResponseEntity <User> createuser(@RequestBody User user){

        if (!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Parol uzuligi 4 dan kam", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserName(user.getUsername())){
            return new ResponseEntity("Bu user Oldin ro'yxatdan o'tgan", HttpStatus.BAD_REQUEST);
        }
        User newuser=userService.create(user);
        return ResponseEntity.ok(newuser);
    }

    private Boolean checkPasswordLength(String password){
        return password.length() >= 4;
    }


    @RequestMapping("/access_denied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("access-denied");
        return modelAndView;
    }


        @GetMapping("/user/login")
   public ResponseEntity<String> showLogin(@RequestParam(value = "error", required = false) String error) {
        if (error != null) {
          return new ResponseEntity<>("Wrong",HttpStatus.OK);
      }
       return new ResponseEntity<>("Success",HttpStatus.OK);
   }
//wefew
//wefew
    @RequestMapping("/user/login")
    public ModelAndView showLogin() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        if (loggedInUser.getName().equals("anonymousUser")) {
            return new ModelAndView("user-login");
        }
        return new ModelAndView("redirect:/");

    }



    @GetMapping("/verifyingUser/{id}/{token}")
    public ModelAndView verifyingUser(@PathVariable("id") long id, @PathVariable("token") String token) {
       User user = new User();
       user.setId(id);
        if (userService.verifyingUser(user, token)) {
          ModelAndView mv = new ModelAndView("user-login");
            return mv;
       }
        return null;
    }

    @GetMapping("/findbyusername")
    public Optional<User> user() {

        Optional<User> user = userService.findByUsername("partho");

        System.out.println(user.isPresent());
        return
                userService.findByUsername("partho");
    }


    //  CUSTOM ERROR HANDLER

        @RequestMapping("/erroreee")
    public String handleError(HttpServletRequest request) {
        // get error status
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        // log error details here
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            // display specific error page
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "403";
            }
        }
        // display generic error
        return "not-found";
    }


    @PostMapping("/postComment")
    public ResponseEntity<?> postNewsComments( @ModelAttribute("newsComment") NewsComments comment, @RequestParam("newsId") long newsId) {
        Long userId = null;
        String userName = null;
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        try {
            NewsUserDetails s = (NewsUserDetails) loggedInUser.getPrincipal();
            userId = s.getId();
            userName = s.getUsername();
        } catch (Exception e) {
        }
        if (loggedInUser.getPrincipal().equals("anonymousUser")) {
        } else {
            newsCommentsRepository.saveComment(newsId, userId, comment.getComment());
            User user = new User();
            user.setUsername(userName);
            NewsComments newsComments = new NewsComments();
            newsComments.setUser(user);
            newsComments.setComment(comment.getComment());
            return new ResponseEntity(newsComments, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/showComments")
    public ResponseEntity<List<NewsComments>> showComments(@RequestParam("newsid") long newsId) {
        return new ResponseEntity<>(newsCommentsRepository.showAllCommentsByNewsId(newsId), HttpStatus.OK);
    }


    private final NewsRepository newsRepository;

    @GetMapping("/users")
    public ResponseEntity<List<Users>> showAllUsers() {
        return new ResponseEntity<>(newsRepository.showAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/souravdata")
    public ModelAndView showAllSouravUsers(HttpServletRequest request) {
        try {
            request.login("", "");
        } catch (Exception ex) {

        }
        ModelAndView view = new ModelAndView();
        view.setViewName("souravdata");
        view.addObject("souravdata", newsRepository.showAllUsers());
        return view;
    }

    private final UsersService usersService;

    @GetMapping("/usersem")
    public ResponseEntity<List<Users>> getAllEmployees(
            @RequestParam(defaultValue = "5") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
        List<Users> list = usersService.getAllEmployees(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Users>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/errorEx")
    public void showError() throws MathematicalException {
        int[] arr;
        arr = new int[3];

        System.out.println(arr[5]);
    }

    @GetMapping("/practise")
    public ModelAndView practise() {
        ModelAndView mv = new ModelAndView("practise");
        // mv.setViewName("practise");
        mv.addObject("newsCategory", newsCategoryServices.findAllNewsCategory());
        return mv;
    }

    private final NewsCategoriesAndSubCategoriesRepository categoriesAndSubCategories;


    @GetMapping("/showNewData")
    public ResponseEntity<List<NewsCategoriesAndSubCategories>> showdata() {
        return new ResponseEntity<>(categoriesAndSubCategories.findNewsSubCategoriesByCategoryName("Sports"), HttpStatus.OK);
    }
  /*  @PostMapping("/loginvm")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginVM loginvm) throws Exception {
        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginvm.getUsername(), loginvm.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = newsUserDetailsService.loadUserByUsername(loginvm.getUsername());
        final String jwt = new JwtUtil().generateToken(userDetails);
        return ResponseEntity.ok(new JwtUtil());
    }*/


}