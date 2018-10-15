local userid=KEYS[1];
local prodid=KEYS[2];
local qtkey="sk:"..prodid..":qt";
local userskey="sk:"..prodid..":user";
local userExists=redis.call("sismember",userskey,userid);
if tonumber(userExists)==1 then
    return 2;
else
    redis.call("desr",qtkey);
    redis.call("sadd",userskey,userid);
end
return 1;