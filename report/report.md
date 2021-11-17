<div align="center">

##### Mobile Android Application

# **Report Project: FLICKR IMAGE BROWSER**

&nbsp;

**Group: 11**

**University of Science and Technology of Hanoi**

May 2021

&nbsp; &nbsp;

## **Group members**

```
Lê Anh Tú  BA9-067

Lê Duy  BA9-075

Phạm Tuấn Thành  BA9-057

Đỗ Mạnh Thắng  BA9-056
```  

 </div>

## **TABLE OF CONTENTS**

- [I/ Introduction](#intro)
    + [1. What is Flickr](#intro1)
    + [2. Approaches](#intro2)
    + [3. Preparations](#intro3)
        * [a) Software and tools](#intro31)
        * [b) Library](#intro32)

- [II/ Implementation](#implementation)
    + [1. Planing](#implementation1)
    + [2. Database schema](#implementation2)
        * [a) Relationship between tables](#implementation21)
        * [b) Tables functions](#implementation22)
    + [3. Project Architecture](#implementation3)
    + [4. Classes and Packages](#implementation4)

- [III/ Results](#results)
    + [1. Login](#results1)
    + [2. Sign Up](#results2)
    + [3. Hyper Link](#results3)
    + [4. Newsfeed](#results4)
    + [5. Search](#results5)
    + [6. Profile](#results6)

- [IV/ Conclusion](#conclusion)
  + [1. Contribution](#con1)
  + [2. What we have not done](#con2)
  + [3. What we have done](#con3)

## I/ Introduction <a name="intro"></a>

### 1. What is Flickr <a name="intro1"></a>

&ensp;&ensp;&ensp;Flickr is a photo-centric platform that allows professionals and amateur photographers to showcase
their work while also admiring the work of others. It devotes more attention to the art of photography than any other
major social media platform. There are amount of features of Flickr:

+ Profile: You obtain a profile on Flickr, just like any other social network, where you may post a user photo and write
  a brief bio.
+ Albums: To keep your photo collections nice and neat, organize them into albums. You can share entire albums with
  friends.
+ Groups: Groups are based around a central topic or idea and allow users to freely join. Members of a group can share
  their photos and participate in group discussions.
+ Organize: Use Flickr's unique Organizr tool to edit your photos and arrange them just the way you want.

### 2. Approaches <a name="intro2"></a>

&ensp;&ensp;&ensp;First the program is designed with a convenient and easy-to-use interface, easy to look up. Flickr is
one of the big names when it comes to image and video sharing platforms. Flickr encourages numerous activities that the
users can take part in, including photo sharing, video sharing, photo and video hosting, browsing through its wide
collection of photos and videos and more.

&ensp;&ensp;&ensp;Flickr is a well-known photo-sharing and storage site that offers a variety of complex and powerful
features. It encourages users to share and examine one other's images, resulting in a vibrant and involved community.
Without spending a dollar, you can share and host hundreds of your own photos on Flickr.

### 3. Preparations <a name="intro3"></a>

#### a) Software and Tools <a name="intro31"></a>

- OS: Windows 10
- Environment: Android SDK 11
- Database: SQLite
- Storage: Github
- IDE: Android Studio

#### b) Library <a name="intro32"></a>

    + navigation-fragment:2.3.5
    + navigation-ui:2.3.5
    + volley:1.2.1
    + link_builder:2.0.5
    + glide:4.12.0
    + compiler:4.12.0
    + picasso:2.71828

## II/ Implementation <a name="implementation"></a>

### 1. Planing <a name="implementation1"></a>

&ensp;&ensp;&ensp;To implement this project, we came up with the idea of creating 6 functions that perform different
tasks of the flickr:

```
Login:
    Input validation
    Register

Newsfeed:
    List of posts.
    Main image.
    Like and quantity.
    Comment and quantity.
    Comment fragment.
    User name
    Title
    Share
    
Search:
    Search bar.
    Default list of image.
    Photo, People, Groups fragmnet.
    
Camera:
    Galleries.
    Post image.
        
Notification:
    List of notification.
    
Profile:
    Background image.
    Buddy icon.
    Camera roll.
    Public.
    Albums.
    Groups.
    Stats.
    Sort function.    
```

### 2. Database Schema <a name="implementation2"></a>

&ensp;&ensp;&ensp;**Database** is an organized collection of structured information or data, usually stored in
electronic form in our systems. The diagram below is drawn to show the structure of the database. By using the database
diagram, we can create and modify the login and registration information of the user.

### 3. Project Architecture <a name="implementation3"></a>

&ensp;&ensp;&ensp;Model-View-ViewModel (MVVM) is a software design pattern that is structured to separate program logic
and user interface controls. MVVM is also known as model-view-binder.

&ensp;&ensp;&ensp;Like many other design patterns, MVVM helps organize code and break programs into modules to make
development, updating and reuse of code simpler and faster. The separation of the code in MVVM is divided into View,
ViewModel and Model:

+ View is the collection of visible elements, which also receives user input. This includes user interfaces (UI),
  animations and text. The content of View is not interacted with directly to change what is presented.


+ ViewModel is located between the View and Model layers. This is where the controls for interacting with View are
  housed, while binding is used to connect the UI elements in View to the controls in ViewModel.


+ Model houses the logic for the program, which is retrieved by the ViewModel upon its own receipt of input from the
  user through View.

![](image/mvvm.png)

### 4. Classes and Packages <a name="implementation4"></a>

&ensp;&ensp;&ensp;In real implementation, we separate classes into 8 package, each package do difference work:

![](image/packages.png)

1. Adapter is a class that have duty as a data controller and helps us to fill data in UI component. It holds the data
   and send the data to an Adapter view then view can take the data from the adapter view and shows the data on
   different recycler view in different fragment


2. API package responsible for performing and authorization for Api services.


3. Database package simply a single class that accessed, managed, modified, updated data for log in and sign up
   function.


4. The model implements business logic, represents the actual data and/or information we are dealing with. An example of
   a model might be a contact (containing name, phone number, address, etc.) or the characteristics of a live-streaming
   publishing point.


5. The objective of the repository package is to provide a clean API for accessing data. That is, the Repository can
   collect data from a variety of sources (including REST APIs, caching, and local database storage) and distribute it
   to the rest of the program. It also acts as a centralized source of information. Its job is to keep the local
   database updated with the most recent data obtained from the distant service so that the program may continue to
   function.


6. User interface (ui package) is a part of MVVM also call View. The structure, arrangement, and appearance of
   fragments. It shows a model representation and accepts user input (mouse clicks, keyboard input, screen tap gestures,
   and so on) and passes it on to the view model via the data binding (properties, event callbacks, and so on) that is
   defined to connect the view and view model.


7. The idea of utils was born will the idea that store utilities class with many helpful method inside which will make
   the hold concept faster and efficient


8. Final and the most important part, View-Model or binder is in charge of managing the interaction of the View with the
   relevant model classes. The ViewModel and Model classes usually have a one-to-many relationship. Views can select to
   expose model classes to the View directly, allowing controls in the View to connect data to them directly. Data
   binding and accompanying change notification events must be supported. The code below explain how we implement:

```java
public class NewsFeedViewModel extends ViewModel {
    private static MutableLiveData<ArrayList<NewsFeedPost>> newsFeedPosts = new MutableLiveData<>();
    private static NewsFeedViewModel instance;
    private static NewsFeedRepository newsFeedRepository;
    private static ArrayList<NewsFeedPost> list;

    private NewsFeedViewModel() {
    }

    public static NewsFeedViewModel getInstance() {
        if (instance == null) {
            instance = new NewsFeedViewModel();
            newsFeedRepository = NewsFeedRepository.getInstance();
            setNewsFeedPosts();
        }
        return instance;
    }

    public static void setNewsFeedPosts() {
        list = newsFeedRepository.fetchNewsFeed(); // get value
        newsFeedPosts = new MutableLiveData<>(list);
    }

    /*
     * updateNewsFeed = new value => append to list
     * => list updated => onChanged => observed
     * */
    public static void updateNewsFeedPosts() {
        ArrayList<NewsFeedPost> updateNewsFeed = newsFeedRepository.updateNewsFeed(); // get val
        list.addAll(updateNewsFeed);
        newsFeedPosts.postValue(list);
    }

    public LiveData<ArrayList<NewsFeedPost>> getNewsFeedPosts() {
        getInstance();
        return newsFeedPosts;
    }
}
```

And how Views can connect to View-Model at NewsFeedFragment.java

```java
public void setUpRecyclerViewDataFromViewModel(View view){
        setRecyclerViewWaiter(view);
        afterFinishGetDataOnBackGround(()->{
        setRecyclerViewRealData();
        NewsFeedAdapterRV.setReady(true);
        observeData();
        });
        }
```

## III/. Results <a name="results"></a>

### 1. Login <a name="results1"></a>

> Login Fragment is a content of Fragment View Container that contain many component as input to connect and perform query from database.
> <br>
> In this image EditText is doing his work when alert people when they are wrong.

<img src="image/login.png" alt="s" width="270" height="555" />

### 2. Sign Up <a name="results2"></a>

> Sign up fragment is also like Login. It contains 5 EditText that input user value into a filter then push everything
> up to database as an account for login.
>
<img src="image/signup.png" alt="s" width="270" height="555" />

### 3. Hyper Link <a name="results3"></a>

> As you can see in the two previous images. There is many hyperlink around such as "Term of Service", "Privacy Policy", ...
> That will lead user to Flickr web policy
>
<img alt="s" src="image/web.png" width="270" height="555" />

### 4. Newsfeed <a name="results4"></a>

> The most important part is newsfeed, newsfeed simply an infinity list of newsfeed post.
> <br>
> Newsfeed post is a structure of two main image: avatar and main image then flow by the owner name and title
>
<img src="image/newsfeed.png" alt="s" width="270" height="555" />

> An interest point here is when user rotate device, the device will kill all things in it life cycle
> but with MVVM above data saved from life cycle event.
>
<img src="image/newsfeed_land.png" alt="s" width="555" height="270" />

### 5. Search <a name="results5"></a>

> As mention above Flickr provide search feature that help user can perform interest things such as: tags, people,
> events, ...
>
<img src="image/search.png" alt="s" width="270" height="555" />

### 6. Profile <a name="results6"></a>

> In profile, you can change your profile photo to set as your primary Flickr picture. Your Flickr profile photo is
> the primary icon that displays when others view your account.
> <br>
> But we didn't have enough time and skills to perform this feature yet.
>
<img src="image/profile.png" alt="s" width="270" height="555" />

## IV/ Conclusions <a name="conclusion"></a>

### 1. Contribution<a name="con1"></a>

1. Le Anh Tu: 25%
2. Do Manh Thang: 25%
3. Pham Tuan Thanh: 25%
4. Le Duy: 25%

### 2. What we have not done<a name="con2"></a>

1. Optimization
    + Poor thread
2. Feature missing
    + Camera
    + Notification
    + Profile
3. Bugs
    + Crash

> We came up with many idea and feature as you can see at part II.1, but we didn't finish because of skills limitation and
> some difficulties.

### 3. What we have done<a name="con3"></a>

1. 60% in progress
    + Thread
    + Singleton
    + Callback
2. Try our best
    + Recommend technique
    + Correct idea
3. Clean Architecture
4. Real API connection

> Even 60% of project, we still learn many things because we were trying the best. Trying to implement recommend technique
> by Google at https://developer.android.com/guide/ and the idea we build project from crash is correct and simulate
> professional working environment. Flick Android is quite a success project. 



