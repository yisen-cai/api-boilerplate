redis.call("set", KEYS[1], KEYS[2])
local result = tonumber(redis.call("incr", KEYS[1]))
redis.call("set", KEYS[3], result)
return result