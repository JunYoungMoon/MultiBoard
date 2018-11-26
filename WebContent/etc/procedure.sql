CREATE OR REPLACE PROCEDURE pro_log (p_id in varchar2,
                                     p_pwd in varchar2,
                                     p_name in varchar2,
                                     p_ip in varchar2,
                                     checknum out number) is

/*e_nomem exception;*/
  i_pwd varchar2(4000);
  
begin  

  select pwd
  into i_pwd
  from member
  where id = p_id;
  
  /*if sql%notfound then
  raise e_nomem;
  end if;*/
  
  if i_pwd = p_pwd then
    insert into logrec values
    (log_seq.nextval, p_id, p_name, 'login', sysdate, p_ip);
    checknum := 1;
  else
    checknum := 0;
  end if; 
  
 exception
  when NO_DATA_FOUND then
  checknum := -1;
end;


  