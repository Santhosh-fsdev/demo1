package com.example.demo.controller;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.Utils.Jwtutil;
import com.example.demo.model.Data;

import com.example.demo.model.Request;
import com.example.demo.model.Response;
import com.example.demo.repository.DataRepository;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.UserService;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.GetMapping;

//Cloudinary credentials
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin //enables cors requests
@RestController
public class MainController {

    //Bean created in config -> SecurityConfigurer
    @Autowired
    private AuthenticationManager authenticationManager;

    //for performing jwt operations Utils -> JwtUtil
    @Autowired
    private Jwtutil jwtutil;

    //returns and inmemory user service -> UserService
    @Autowired
    private UserService userService;

    //which performs the db operations repository ->DataRepository
    @Autowired
    private DataRepository repository;

    //Performs Cloudinary Service
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private Cloudinary cloudinary;



    @GetMapping("/todos")
    public List<Data> data(){
        System.out.println("get method");
        return repository.findAll();
        
    }

    ArrayList<Data> list=new ArrayList<Data>();
    @PostMapping("/save")
    public String Todos(@RequestBody Data data2){
       repository.save(data2);
       return "Saved Successfully";

    }
    


     @DeleteMapping("/deleteTodo/{id}")
        public String  deleteAll(@PathVariable (value="id")String id) {
            repository.deleteById(id);
            return "Todo Deleted";
            
    }
//    @PutMapping("/updateTodo/{id}")
//        public Data Update(@PathVariable (value = "id") String id){
//          Data data3 = new Data();
//          data3 = repository.findById(id);
//
//    }

@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
public ResponseEntity<?> createAuthenticationToken(@RequestBody Request authenticationRequest) throws Exception {

        //checking user credentials
    try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
    }
    catch (BadCredentialsException e) {
        throw new Exception("Incorrect username or password", e);
    }

    //calling user details services which returns user detail
    final UserDetails userDetails = userService
            .loadUserByUsername(authenticationRequest.getUsername());

    //generating token -> jwtutil
    final String jwt = jwtutil.generateToken(userDetails);

    //setting response with token if authentication is true
    return ResponseEntity.ok(new Response(jwt));
}


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        //calling the cloudinary service to upload files
        String url = cloudinaryService.uploadFile(file);
        return "File uploaded successfully: File path :  " + url;
    }

    String api = "api_key";

    @GetMapping("/getfiles")
    public ApiResponse files() throws Exception {

        //it returns all the url of the assests in my storage
         ApiResponse result = cloudinary.search()
                .execute();

         System.out.println(result);

         return result;

    }

}
