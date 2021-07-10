package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// ����Ŭ �����ͺ��̽��� �����ϰ� select, insert, update, delete�۾��� �������ִ� Ŭ����
public class MemberDAO {
	// ����Ŭ�� �����ϴ� �ҽ� �ۼ�
//	String id = "hr2";
//	String pass = "1234";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // ���� url
	
	Connection con; // �����ͺ��̽��� �����Ҽ��ֵ��� ����
	PreparedStatement pstmt; // �����ͺ��̽����� ������ ��������ִ� ��ü ����
	ResultSet rs; // �����ͺ��̽��� ���̺��� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü
	
	// �����ͺ��̽��� �����Ҽ��ֵ��� �����ִ� �޼ҵ�
	public void getCon() {
		// Ŀ�ؼ�Ǯ�� �̿��Ͽ� �����ͺ��̽��� ����
		try {
			// �ܺο��� �����͸� �о�帲
			Context initctx = new InitialContext();
			// ��Ĺ ������ ������ ��Ƴ��� ������ �̵�
			Context envctx = (Context) initctx.lookup("java:comp/env");
			// ������ �ҽ� ��ü�� ����
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			// ������ �ҽ��� �������� Ŀ�ؼ��� ����
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		try {
//			// 1. �ش� �����ͺ��̽��� ����Ѵٰ� ����(Ŭ������ ���=����Ŭ��)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			// 2. �ش� �����ͺ��̽��� ����
//			con = DriverManager.getConnection(url, id, pass);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	// �����ͺ��̽��� �ѻ���� ȸ�� ������ �������ִ� �޼ҵ�
	public void insertMember(MemberBean mbean) {
		try {
			getCon();
			// 3. ������ �����غ�
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			// ������ ����ϵ��� ����
			PreparedStatement pstmt = con.prepareStatement(sql); // jsp���� ������ ����ϵ��� ����
			// ?�� �°� �����͸� ����
			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPass1());
			pstmt.setString(3, mbean.getEmail());
			pstmt.setString(4, mbean.getTel());
			pstmt.setString(5, mbean.getHobby());
			pstmt.setString(6, mbean.getJob());
			pstmt.setString(7, mbean.getAge());
			pstmt.setString(8, mbean.getInfo());
			
			// 4.  ����Ŭ���� ���� ����
			pstmt.executeUpdate(); // insert, update, delete �� ����ϴ� �޼���
			
			// 5. �ڿ� �ݳ�
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// ��� ȸ���� ������ �������ִ� �޼ҵ� ȣ��
	public Vector<MemberBean> allSelectMember() {
		// �������̷� �����͸� ����
		Vector<MemberBean> v = new Vector<>();
		
		// ������ �����ͺ��̽��� ����ó���� �ݵ�� �ؾ���.
		try {
			// Ŀ�ؼ� ����
			getCon();
			
			// ���� �غ�
			String sql = "select * from member";
			// ������ ��������ִ� ��ü ����
			pstmt = con.prepareStatement(sql);
			// ������ �����Ų ����� ���Ͻ��� �޾���(����Ŭ ���̺��� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			// �ݺ����� ����ؼ� rs�� ����� �����͸� ����
			while(rs.next()) { // ����� �����͸�ŭ���� �ݺ����� �����ڴٴ� ��
				MemberBean bean = new MemberBean(); // �÷����� �������� �����͸� ��Ŭ���忡 ����(��� ���Ӱ� ����)
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				// ��Ű¡�� memberBeanŬ������ ���Ϳ� ����
				v.add(bean); // 0������ ������� �����Ͱ� ����
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// �� ����� ���͸� ����
		return v;
	}
	
	// �ѻ���� ���� ������ �����ϴ� �޼ҵ� �ۼ�
	public MemberBean oneSelectMember(String id) {
		// �ѻ���� ���� ������ �����ϱ⿡ ��Ŭ���� ��ü����
		MemberBean bean = new MemberBean();
		
		try {
			// Ŀ�ؼ� ����
			getCon();
			// �����غ�
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			// ?�� ���� ����
			pstmt.setString(1, id);
			// ��������
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // �÷��� �ִٸ�
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
			}
			// �ڿ� �ݳ�
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// ����
		return bean;
	}
	
	// �� ȸ���� �н����� ���� �����ϴ� �޼ҵ�
	public String getPass(String id) {
		// ��Ʈ������ ������ �ؾ��ϱ⿡ ��Ʈ�� ���� ����
		String pass = "";
		
		try {
			getCon();
			
			// �����غ�
			String sql = "select pass1 from member where id=?";
			pstmt = con.prepareStatement(sql);
			// ?�� ���� ����
			pstmt.setString(1, id);
			// ���� ����
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pass = rs.getString(1); // �н����� ���� ����� �÷��ε���
			}
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pass;
	}
	
	// �� ȸ���� ������ �����ϴ� �޼ҵ�
	public void updateMember(MemberBean bean) {
		getCon();
		
		try {
			// �����غ�
			String sql = "update member set email=?, tel=? where id=?";
			// �������� ��ü ����
			pstmt = con.prepareStatement(sql);
			// ?�� ���� ����
			pstmt.setString(1, bean.getEmail());
			pstmt.setString(2, bean.getTel());
			pstmt.setString(3, bean.getId());
			
			// ��������
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	// �� ȸ���� �����ϴ� �޼ҵ�
	public void deleteMember(String id) {
		getCon();
		
		try {
			// �����غ�
			String sql = "delete from member where id=?";
			// �������� ��ü����
			pstmt = con.prepareStatement(sql);
			// ?�� ���� ����
			pstmt.setString(1, id);
			// ��������
			pstmt.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}