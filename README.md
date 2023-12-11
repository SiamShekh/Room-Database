# Dependeciy
            implementation 'androidx.room:room-common:2.4.2'
            implementation 'androidx.room:room-runtime:2.4.2'
            annotationProcessor 'androidx.room:room-compiler:2.4.2'

# Entity
>create entity

            @Entity
            public class User {
                @PrimaryKey (autoGenerate = true)
                public int uid;
            
                @ColumnInfo(name = "first_name")
                public String firstName;
            
                @ColumnInfo(name = "last_name")
                public String lastName;
            
                public User(int uid, String firstName, String lastName) {
                    this.uid = uid;
                    this.firstName = firstName;
                    this.lastName = lastName;
                }
            
                public int getUid() {
                    return uid;
                }
            
                public void setUid(int uid) {
                    this.uid = uid;
                }
            
                public String getFirstName() {
                    return firstName;
                }
            
                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }
            
                public String getLastName() {
                    return lastName;
                }
            
                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }
            }

#Dao
> Create @Dao

            @Dao
            public interface UserDao {
                @Insert
                void insertrecord(User users);
                
                @Query("SELECT EXISTS(SELECT * FROM User WHERE uid = :userId)")
                Boolean is_exist(int userId);
            
                @Query("SELECT * FROM User")
                List<User> getallusers();
            
                @Query("DELETE FROM User WHERE uid = :id")
                void deleteById(int id);
            
                @Query("UPDATE User SET first_name = :fname, last_name=:lname WHERE uid = :id")
                void updateById(int id, String fname, String lname);
            }

# Database
> Create Room Database

            @Database(entities = {User.class}, version = 1)
            public abstract class RoomDatabases extends androidx.room.RoomDatabase {
                public abstract UserDao userDao();
            }

# Fetch data
> Fetch from Room
      
          public void getroomdata() {
              RoomDatabases db = Room.databaseBuilder(getApplicationContext(), RoomDatabases.class, "room_db")
                      .allowMainThreadQueries()
                      .build();
      
              UserDao userDao = db.userDao();
      
              recview = findViewById(R.id.recview);
              recview.setLayoutManager(new LinearLayoutManager(this));
      
              List<User> users = userDao.getallusers();
      
              CustomAdapter adapter = new CustomAdapter(users);
              recview.setAdapter(adapter);
          }

# Update data
> Update room

              RoomDatabases db = Room.databaseBuilder(getApplicationContext(),RoomDatabases.class, "room_db")
                                      .allowMainThreadQueries()
                                      .build();
                              UserDao userDao = db.userDao();
                              userDao.updateById(uid, fname.getText().toString(), lname.getText().toString());

# Delete Data
             RoomDatabases db = Room.databaseBuilder(holder.recid.getContext(),
                                    RoomDatabases.class, "room_db").allowMainThreadQueries().build();
                            UserDao userDao = db.userDao();
                            // this is to delete the record from room database
                            userDao.deleteById(users.get(position).getUid());
                            // this is to delete the record from Array List which is the source of recview data
                            users.remove(position);
            
                            //update the fresh list of ArrayList data to recview
                            notifyDataSetChanged();
