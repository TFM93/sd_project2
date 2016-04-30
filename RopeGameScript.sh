#!/bin/bash
username=sd0203
password=diogocardoso
confFile=rg.config

# Read configuration file
while read line
do
    splitIndex=(${line//=/ })
    value=${line##*=}

    if [[ "$splitIndex" =~ "REPO_HOST_NAME" ]]; then
    	repositoryHostName=${value}
    elif [[ "$line" =~ "REPO_PORT_NUM" ]]; then
    	repositoryPortNum=${value}
    elif [[ "$line" =~ "REFSITE_HOST_NAME" ]]; then
    	refSiteHostName=${value}
    elif [[ "$line" =~ "REFSITE_PORT_NUM" ]]; then
    	refSitePortNum=${value}
    elif [[ "$line" =~ "BENCH_HOST_NAME" ]]; then
    	benchHostName=${value}
    elif [[ "$line" =~ "BENCH_PORT_NUM" ]]; then
    	benchPortNum=${value}
    elif [[ "$line" =~ "PLAYG_HOST_NAME" ]]; then
    	playgHostName=${value}
    elif [[ "$line" =~ "PLAYG_PORT_NUM" ]]; then
    	playgPortNum=${value}
    elif [[ "$line" =~ "REFEREE_HOST_NAME" ]]; then
    	refereeHostName=${value}
    elif [[ "$line" =~ "COACH_HOST_NAME" ]]; then
    	coachHostName=${value}
    elif [[ "$line" =~ "CONTESTANT_HOST_NAME" ]]; then
    	contestantHostName=${value}
    elif [[ "$line" =~ "CONFIG_HOST_NAME" ]]; then
    	configurationHostName=${value}
    elif [[ "$line" =~ "CONFIG_PORT_NUM" ]]; then
    	configurationPortNum=${value}
    fi
done < $confFile


printf "\nClosing all active servers and clients ...\n"
{
sshpass -p $password ssh $username@$configurationHostName killall -u sd0203
sshpass -p $password ssh $username@$repositoryHostName killall -u sd0203
sshpass -p $password ssh $username@$refSiteHostName killall -u sd0203
sshpass -p $password ssh $username@$benchHostName killall -u sd0203
sshpass -p $password ssh $username@$playgHostName killall -u sd0203
sshpass -p $password ssh $username@$refereeHostName killall -u sd0203
sshpass -p $password ssh $username@$coachHostName killall -u sd0203
sshpass -p $password ssh $username@$contestantHostName killall -u sd0203
} &> /dev/null




printf "Sending configuration file to Configuration Host...\n"
sshpass -p $password scp $confFile $username@$configurationHostName:/home/$username &> /dev/null
sleep 2

printf "\nStarting Configuration Server at($configurationHostName, $configurationPortNum) ...\n"
sshpass -p $password ssh -f $username@$configurationHostName java -jar configuration.jar $configurationPortNum $confFile
sleep 2

printf "\nStarting Repository Server at($repositoryHostName, $repositoryPortNum) ...\n"
sshpass -p $password ssh -f $username@$repositoryHostName java -jar repository.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting RefereeSite Server at($refSiteHostName, $refSitePortNum) ...\n"
sshpass -p $password ssh -f $username@$refSiteHostName java -jar refSite.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Bench Server at($benchHostName, $benchPortNum) ...\n"
sshpass -p $password ssh -f $username@$benchHostName java -jar bench.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting PlayGround Server at($playgHostName, $playgPortNum) ...\n"
sshpass -p $password ssh -f $username@$playgHostName java -jar playg.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Referee Client at($refereeHostName) ...\n"
sshpass -p $password ssh -f $username@$refereeHostName java -jar referee.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Coach Client at($coachHostName) ...\n"
sshpass -p $password ssh -f $username@$coachHostName java -jar coach.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Contestant Client at($contestantHostName) ...\n"
sshpass -p $password ssh -f $username@$contestantHostName java -jar contestant.jar $configurationHostName $configurationPortNum
sleep 2
