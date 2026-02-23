-- ============================================
-- 1. テーブル作成
-- ============================================

DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(130) NOT NULL,
    employee_no VARCHAR(10) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(12,2) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================
-- 2. 初期データ投入
-- ============================================

INSERT INTO employees (name, department, salary) VALUES
('Suzuki', 'Sales', 310000),
('Sato', 'IT', 400000),
('Kato', 'IT', 450000),
('Yamada', 'HR', 280000);
('Watanabe', 'HR', 290000);

-- ============================================
-- 3. 全件取得
-- ============================================

SELECT *
FROM employees
ORDER BY id;

-- ============================================
-- 4. 部署別検索
-- ============================================

SELECT id, name, salary
FROM employees
WHERE department = 'IT'
ORDER BY salary DESC;

-- ============================================
-- 5. 平均給与
-- ============================================

SELECT AVG(salary) AS avg_salary
FROM employees;

-- ============================================
-- 6. 部署別平均給与
-- ============================================

SELECT department,
       COUNT(*) AS employee_count,
       AVG(salary) AS avg_salary,
       MAX(salary) AS max_salary,
       MIN(salary) AS min_salary
FROM employees
GROUP BY department
ORDER BY avg_salary DESC;

-- ============================================
-- 7. 昇給処理（IT部門10%アップ）
-- ============================================

UPDATE employees
SET salary = salary * 1.10
WHERE department = 'IT';

-- ============================================
-- 8. 高額社員ランキング（上位3名）
-- ============================================

SELECT id, name, department, salary
FROM employees
ORDER BY salary DESC
LIMIT 3;

-- ============================================
-- 9. 500,000以上の社員がいる部署を抽出
-- ============================================

SELECT DISTINCT department
FROM employees
WHERE salary >= 500000;

-- ============================================
-- 10. インデックス追加（パフォーマンス改善）
-- ============================================

CREATE INDEX idx_department ON employees(department);
CREATE INDEX idx_salary ON employees(salary);