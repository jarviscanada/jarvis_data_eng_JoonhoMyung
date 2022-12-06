#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
  echo 'Please type in 5 arguments'
  echo 'psql host | psql port# | db_name | psql username | psql password'
  exit 1
fi

lscpu_out=`lscpu`
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)


cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "Model name:" | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "CPU MHz:" | awk '{print $3}' | xargs)
L2_cache=$(echo "$lscpu_out"  | egrep "L2 cache:" | awk '{$3=substr($3,1,length($3)-1); print $3}'  | xargs)
total_mem=$(cat /proc/meminfo | egrep "MemTotal:" | awk '{print $2}'  | xargs)
timestamp=$(vmstat -t  | awk '{print $18, $19}' | tail -n1  | xargs)


insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture,
                                   cpu_model, cpu_mhz, L2_cache, total_mem,
                                   timestamp)
             VALUES('$hostname', '$cpu_number', '$cpu_architecture',
                    '$cpu_model', '$cpu_mhz', '$L2_cache', '$total_mem', '$timestamp')"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?