package ru.avsidorov.github.MODELS;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GHCommit {

    @Expose
    private String url;
    @Expose
    private String sha;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @Expose
    private Commit commit;
    @Expose
    private Author_ author;
    @Expose
    private Committer_ committer;


    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public GHCommit withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return The sha
     */
    public String getSha() {
        return sha;
    }

    /**
     * @param sha The sha
     */
    public void setSha(String sha) {
        this.sha = sha;
    }

    public GHCommit withSha(String sha) {
        this.sha = sha;
        return this;
    }

    /**
     * @return The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * @param htmlUrl The html_url
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public GHCommit withHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    /**
     * @return The commentsUrl
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     * @param commentsUrl The comments_url
     */
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public GHCommit withCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
        return this;
    }

    /**
     * @return The commit
     */
    public Commit getCommit() {
        return commit;
    }

    /**
     * @param commit The commit
     */
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public GHCommit withCommit(Commit commit) {
        this.commit = commit;
        return this;
    }

    /**
     * @return The author
     */
    public Author_ getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Author_ author) {
        this.author = author;
    }

    public GHCommit withAuthor(Author_ author) {
        this.author = author;
        return this;
    }

    /**
     * @return The committer
     */
    public Committer_ getCommitter() {
        return committer;
    }

    /**
     * @param committer The committer
     */
    public void setCommitter(Committer_ committer) {
        this.committer = committer;
    }

    public GHCommit withCommitter(Committer_ committer) {
        this.committer = committer;
        return this;
    }


    public class Author {

        @Expose
        private String name;
        @Expose
        private String email;
        @Expose
        private String date;

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        public Author withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * @return The email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        public Author withEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * @return The date
         */
        public String getDate() {
            return date;
        }

        /**
         * @param date The date
         */
        public void setDate(String date) {
            this.date = date;
        }

        public Author withDate(String date) {
            this.date = date;
            return this;
        }

    }


    public class Author_ {

        @Expose
        private String login;
        @Expose
        private Integer id;
        @SerializedName("avatar_url")
        @Expose
        private String avatarUrl;
        @SerializedName("gravatar_id")
        @Expose
        private String gravatarId;
        @Expose
        private String url;
        @SerializedName("html_url")
        @Expose
        private String htmlUrl;
        @SerializedName("followers_url")
        @Expose
        private String followersUrl;
        @SerializedName("following_url")
        @Expose
        private String followingUrl;
        @SerializedName("gists_url")
        @Expose
        private String gistsUrl;
        @SerializedName("starred_url")
        @Expose
        private String starredUrl;
        @SerializedName("subscriptions_url")
        @Expose
        private String subscriptionsUrl;
        @SerializedName("organizations_url")
        @Expose
        private String organizationsUrl;
        @SerializedName("repos_url")
        @Expose
        private String reposUrl;
        @SerializedName("events_url")
        @Expose
        private String eventsUrl;
        @SerializedName("received_events_url")
        @Expose
        private String receivedEventsUrl;
        @Expose
        private String type;
        @SerializedName("site_admin")
        @Expose
        private Boolean siteAdmin;

        /**
         * @return The login
         */
        public String getLogin() {
            return login;
        }

        /**
         * @param login The login
         */
        public void setLogin(String login) {
            this.login = login;
        }

        public Author_ withLogin(String login) {
            this.login = login;
            return this;
        }

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        public Author_ withId(Integer id) {
            this.id = id;
            return this;
        }

        /**
         * @return The avatarUrl
         */
        public String getAvatarUrl() {
            return avatarUrl;
        }

        /**
         * @param avatarUrl The avatar_url
         */
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public Author_ withAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        /**
         * @return The gravatarId
         */
        public String getGravatarId() {
            return gravatarId;
        }

        /**
         * @param gravatarId The gravatar_id
         */
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        public Author_ withGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
            return this;
        }

        /**
         * @return The url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public Author_ withUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * @return The htmlUrl
         */
        public String getHtmlUrl() {
            return htmlUrl;
        }

        /**
         * @param htmlUrl The html_url
         */
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public Author_ withHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
            return this;
        }

        /**
         * @return The followersUrl
         */
        public String getFollowersUrl() {
            return followersUrl;
        }

        /**
         * @param followersUrl The followers_url
         */
        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public Author_ withFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
            return this;
        }

        /**
         * @return The followingUrl
         */
        public String getFollowingUrl() {
            return followingUrl;
        }

        /**
         * @param followingUrl The following_url
         */
        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public Author_ withFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
            return this;
        }

        /**
         * @return The gistsUrl
         */
        public String getGistsUrl() {
            return gistsUrl;
        }

        /**
         * @param gistsUrl The gists_url
         */
        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        public Author_ withGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
            return this;
        }

        /**
         * @return The starredUrl
         */
        public String getStarredUrl() {
            return starredUrl;
        }

        /**
         * @param starredUrl The starred_url
         */
        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        public Author_ withStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
            return this;
        }

        /**
         * @return The subscriptionsUrl
         */
        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        /**
         * @param subscriptionsUrl The subscriptions_url
         */
        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        public Author_ withSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
            return this;
        }

        /**
         * @return The organizationsUrl
         */
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        /**
         * @param organizationsUrl The organizations_url
         */
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        public Author_ withOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
            return this;
        }

        /**
         * @return The reposUrl
         */
        public String getReposUrl() {
            return reposUrl;
        }

        /**
         * @param reposUrl The repos_url
         */
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        public Author_ withReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
            return this;
        }

        /**
         * @return The eventsUrl
         */
        public String getEventsUrl() {
            return eventsUrl;
        }

        /**
         * @param eventsUrl The events_url
         */
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public Author_ withEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
            return this;
        }

        /**
         * @return The receivedEventsUrl
         */
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        /**
         * @param receivedEventsUrl The received_events_url
         */
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public Author_ withReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
            return this;
        }

        /**
         * @return The type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type The type
         */
        public void setType(String type) {
            this.type = type;
        }

        public Author_ withType(String type) {
            this.type = type;
            return this;
        }

        /**
         * @return The siteAdmin
         */
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        /**
         * @param siteAdmin The site_admin
         */
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        public Author_ withSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
            return this;
        }

    }


    public class Commit {

        @Expose
        private String url;
        @Expose
        private Author author;
        @Expose
        private Committer committer;
        @Expose
        private String message;
        @Expose
        private Tree tree;
        @SerializedName("comment_count")
        @Expose
        private Integer commentCount;

        /**
         * @return The url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public Commit withUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * @return The author
         */
        public Author getAuthor() {
            return author;
        }

        /**
         * @param author The author
         */
        public void setAuthor(Author author) {
            this.author = author;
        }

        public Commit withAuthor(Author author) {
            this.author = author;
            return this;
        }

        /**
         * @return The committer
         */
        public Committer getCommitter() {
            return committer;
        }

        /**
         * @param committer The committer
         */
        public void setCommitter(Committer committer) {
            this.committer = committer;
        }

        public Commit withCommitter(Committer committer) {
            this.committer = committer;
            return this;
        }

        /**
         * @return The message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message The message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        public Commit withMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * @return The tree
         */
        public Tree getTree() {
            return tree;
        }

        /**
         * @param tree The tree
         */
        public void setTree(Tree tree) {
            this.tree = tree;
        }

        public Commit withTree(Tree tree) {
            this.tree = tree;
            return this;
        }

        /**
         * @return The commentCount
         */
        public Integer getCommentCount() {
            return commentCount;
        }

        /**
         * @param commentCount The comment_count
         */
        public void setCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
        }

        public Commit withCommentCount(Integer commentCount) {
            this.commentCount = commentCount;
            return this;
        }

    }

    public class Committer {

        @Expose
        private String name;
        @Expose
        private String email;
        @Expose
        private String date;

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        public Committer withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * @return The email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        public Committer withEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * @return The date
         */
        public String getDate() {
            return date;
        }

        /**
         * @param date The date
         */
        public void setDate(String date) {
            this.date = date;
        }

        public Committer withDate(String date) {
            this.date = date;
            return this;
        }

    }

    public class Committer_ {

        @Expose
        private String login;
        @Expose
        private Integer id;
        @SerializedName("avatar_url")
        @Expose
        private String avatarUrl;
        @SerializedName("gravatar_id")
        @Expose
        private String gravatarId;
        @Expose
        private String url;
        @SerializedName("html_url")
        @Expose
        private String htmlUrl;
        @SerializedName("followers_url")
        @Expose
        private String followersUrl;
        @SerializedName("following_url")
        @Expose
        private String followingUrl;
        @SerializedName("gists_url")
        @Expose
        private String gistsUrl;
        @SerializedName("starred_url")
        @Expose
        private String starredUrl;
        @SerializedName("subscriptions_url")
        @Expose
        private String subscriptionsUrl;
        @SerializedName("organizations_url")
        @Expose
        private String organizationsUrl;
        @SerializedName("repos_url")
        @Expose
        private String reposUrl;
        @SerializedName("events_url")
        @Expose
        private String eventsUrl;
        @SerializedName("received_events_url")
        @Expose
        private String receivedEventsUrl;
        @Expose
        private String type;
        @SerializedName("site_admin")
        @Expose
        private Boolean siteAdmin;

        /**
         * @return The login
         */
        public String getLogin() {
            return login;
        }

        /**
         * @param login The login
         */
        public void setLogin(String login) {
            this.login = login;
        }

        public Committer_ withLogin(String login) {
            this.login = login;
            return this;
        }

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        public Committer_ withId(Integer id) {
            this.id = id;
            return this;
        }

        /**
         * @return The avatarUrl
         */
        public String getAvatarUrl() {
            return avatarUrl;
        }

        /**
         * @param avatarUrl The avatar_url
         */
        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public Committer_ withAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        /**
         * @return The gravatarId
         */
        public String getGravatarId() {
            return gravatarId;
        }

        /**
         * @param gravatarId The gravatar_id
         */
        public void setGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
        }

        public Committer_ withGravatarId(String gravatarId) {
            this.gravatarId = gravatarId;
            return this;
        }

        /**
         * @return The url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public Committer_ withUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * @return The htmlUrl
         */
        public String getHtmlUrl() {
            return htmlUrl;
        }

        /**
         * @param htmlUrl The html_url
         */
        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }

        public Committer_ withHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
            return this;
        }

        /**
         * @return The followersUrl
         */
        public String getFollowersUrl() {
            return followersUrl;
        }

        /**
         * @param followersUrl The followers_url
         */
        public void setFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
        }

        public Committer_ withFollowersUrl(String followersUrl) {
            this.followersUrl = followersUrl;
            return this;
        }

        /**
         * @return The followingUrl
         */
        public String getFollowingUrl() {
            return followingUrl;
        }

        /**
         * @param followingUrl The following_url
         */
        public void setFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
        }

        public Committer_ withFollowingUrl(String followingUrl) {
            this.followingUrl = followingUrl;
            return this;
        }

        /**
         * @return The gistsUrl
         */
        public String getGistsUrl() {
            return gistsUrl;
        }

        /**
         * @param gistsUrl The gists_url
         */
        public void setGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
        }

        public Committer_ withGistsUrl(String gistsUrl) {
            this.gistsUrl = gistsUrl;
            return this;
        }

        /**
         * @return The starredUrl
         */
        public String getStarredUrl() {
            return starredUrl;
        }

        /**
         * @param starredUrl The starred_url
         */
        public void setStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
        }

        public Committer_ withStarredUrl(String starredUrl) {
            this.starredUrl = starredUrl;
            return this;
        }

        /**
         * @return The subscriptionsUrl
         */
        public String getSubscriptionsUrl() {
            return subscriptionsUrl;
        }

        /**
         * @param subscriptionsUrl The subscriptions_url
         */
        public void setSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
        }

        public Committer_ withSubscriptionsUrl(String subscriptionsUrl) {
            this.subscriptionsUrl = subscriptionsUrl;
            return this;
        }

        /**
         * @return The organizationsUrl
         */
        public String getOrganizationsUrl() {
            return organizationsUrl;
        }

        /**
         * @param organizationsUrl The organizations_url
         */
        public void setOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
        }

        public Committer_ withOrganizationsUrl(String organizationsUrl) {
            this.organizationsUrl = organizationsUrl;
            return this;
        }

        /**
         * @return The reposUrl
         */
        public String getReposUrl() {
            return reposUrl;
        }

        /**
         * @param reposUrl The repos_url
         */
        public void setReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
        }

        public Committer_ withReposUrl(String reposUrl) {
            this.reposUrl = reposUrl;
            return this;
        }

        /**
         * @return The eventsUrl
         */
        public String getEventsUrl() {
            return eventsUrl;
        }

        /**
         * @param eventsUrl The events_url
         */
        public void setEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
        }

        public Committer_ withEventsUrl(String eventsUrl) {
            this.eventsUrl = eventsUrl;
            return this;
        }

        /**
         * @return The receivedEventsUrl
         */
        public String getReceivedEventsUrl() {
            return receivedEventsUrl;
        }

        /**
         * @param receivedEventsUrl The received_events_url
         */
        public void setReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
        }

        public Committer_ withReceivedEventsUrl(String receivedEventsUrl) {
            this.receivedEventsUrl = receivedEventsUrl;
            return this;
        }

        /**
         * @return The type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type The type
         */
        public void setType(String type) {
            this.type = type;
        }

        public Committer_ withType(String type) {
            this.type = type;
            return this;
        }

        /**
         * @return The siteAdmin
         */
        public Boolean getSiteAdmin() {
            return siteAdmin;
        }

        /**
         * @param siteAdmin The site_admin
         */
        public void setSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
        }

        public Committer_ withSiteAdmin(Boolean siteAdmin) {
            this.siteAdmin = siteAdmin;
            return this;
        }

    }


    public class Parent {

        @Expose
        private String url;
        @Expose
        private String sha;

        /**
         * @return The url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public Parent withUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * @return The sha
         */
        public String getSha() {
            return sha;
        }

        /**
         * @param sha The sha
         */
        public void setSha(String sha) {
            this.sha = sha;
        }

        public Parent withSha(String sha) {
            this.sha = sha;
            return this;
        }

    }


    public class Tree {

        @Expose
        private String url;
        @Expose
        private String sha;

        /**
         * @return The url
         */
        public String getUrl() {
            return url;
        }

        /**
         * @param url The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

        public Tree withUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * @return The sha
         */
        public String getSha() {
            return sha;
        }

        /**
         * @param sha The sha
         */
        public void setSha(String sha) {
            this.sha = sha;
        }

        public Tree withSha(String sha) {
            this.sha = sha;
            return this;
        }

    }
}