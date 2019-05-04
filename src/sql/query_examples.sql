--выбираем активную базу данных
use employees;

--выбираем из таблицы всех сотрудников женского пола у которых имя начинается с буквы F
select first_name, last_name, gender, birth_date, hire_date
  from employees
where gender = 'F' and first_name like 'F%';

-- добавляем нового сотрудника
insert into employees(emp_no, birth_date, first_name, last_name, gender, hire_date)
values (111111, '1987-01-01', 'Vasaya', 'Ivanov', 'M', '2018-01-01');

-- выбираем из таблицы нового сотрудника по идентификатору
select first_name, last_name, gender, birth_date, hire_date
  from employees
where emp_no = 111111;

-- изменяем информацию о новом сотруднике
update employees
  set birth_date ='1987-01-02',
        hire_date = '2018-01-04'
where emp_no = 111111;

--  удаляем сотрудника по идентификатору
delete from employees
where emp_no = 111111;