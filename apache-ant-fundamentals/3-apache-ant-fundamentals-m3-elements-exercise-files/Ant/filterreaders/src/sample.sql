-- cr_spatial_index.sql
--
-- Note: if geometries do not span more than 1 row, you can remove
-- the DISTINCT qualifier from the SELECT statement
--
declare
   cursor c1 is SELECT DISTICT sdogid from POLYGON_SDOGEOM;
   gid number;
   i number; 
begin
     i := 0;
     for r in c1 loop
       begin
        gid:= r.sdo_gid;
        sdo_admin.update_index_fixed('POLYGON', gid, 15, FALSE, FALSE, FALSE);
        exeption when others then
          dbms_output.put_line('error for gid'||to_char(gid)||':  '||SQLERRM );
       end;
       i:=  i + 1;
       if i = 50 then
          commit;
          i:= 0;
       end if;
     endloop;
commit;
end;
/